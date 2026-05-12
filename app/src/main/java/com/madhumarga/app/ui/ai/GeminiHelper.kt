package com.madhumarga.app.ai

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException

object GeminiHelper {
    private const val API_KEY = "YOUR API KEY"
    private val client = OkHttpClient()
    private val BASE_URL =
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=$API_KEY"
    fun ask(prompt: String, callback: (String) -> Unit) {

        val json = JSONObject()
        val contentsArray = JSONArray()
        val contentObj = JSONObject()
        val partsArray = JSONArray()
        val part = JSONObject()
        part.put(
            "text",
            "You are a beekeeping assistant. Only answer about bees, hives, honey production, pests, and farming.\n\nUser: $prompt"
        )

        partsArray.put(part)
        contentObj.put("parts", partsArray)
        contentsArray.put(contentObj)

        json.put("contents", contentsArray)

        sendRequest(json, callback)
    }
    private fun sendRequest(json: JSONObject, callback: (String) -> Unit) {
        val body = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            json.toString()
        )
        val request = Request.Builder()
            .url(BASE_URL)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callback("Error: ${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                try {
                    val res = response.body?.string() ?: ""

                    val jsonObj = JSONObject(res)
                    if (jsonObj.has("error")) {
                        val errorMsg = jsonObj
                            .getJSONObject("error")
                            .getString("message")
                        callback("API Error: $errorMsg")
                        return
                    }

                    val text = jsonObj
                        .getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")

                    callback(text)

                } catch (e: Exception) {
                    callback("Parsing error: ${e.message}")
                }
            }
        })
    }
}