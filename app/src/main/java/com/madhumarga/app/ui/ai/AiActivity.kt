package com.madhumarga.app.ui.ai

import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.madhumarga.app.R.*
import com.madhumarga.app.ai.GeminiHelper

class AiActivity : AppCompatActivity() {

    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_ai)

        recycler = findViewById(id.chatRecycler)
        val input = findViewById<EditText>(id.inputText)
        val send = findViewById<ImageButton>(id.sendBtn)

        adapter = ChatAdapter(messages)

        recycler.layoutManager = LinearLayoutManager(this).apply {
            stackFromEnd = true
        }
        recycler.adapter = adapter

        // ✅ SEND TEXT MESSAGE
        send.setOnClickListener {
            val question = input.text.toString().trim()
            if (question.isEmpty()) return@setOnClickListener

            // ✅ User message
            messages.add(ChatMessage(question, true))
            adapter.notifyItemInserted(messages.size - 1)
            recycler.scrollToPosition(messages.size - 1)

            input.setText("")

            // ✅ Add "Thinking..." message
            val thinkingIndex = messages.size
            messages.add(ChatMessage("Thinking...", false))
            adapter.notifyItemInserted(thinkingIndex)
            recycler.scrollToPosition(messages.size - 1)

            // ✅ Call AI
            GeminiHelper.ask(question) { response ->
                runOnUiThread {

                    // 🔥 Replace "Thinking..." instead of adding new message
                    messages[thinkingIndex] = ChatMessage(response, false)
                    adapter.notifyItemChanged(thinkingIndex)

                    recycler.scrollToPosition(messages.size - 1)
                }
            }
        }
    }
    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        val view = currentFocus ?: android.view.View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}