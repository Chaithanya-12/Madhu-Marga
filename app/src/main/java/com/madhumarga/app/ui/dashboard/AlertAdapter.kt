package com.madhumarga.app.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhumarga.app.databinding.ItemAlertBinding

enum class AlertType { WARNING, INFO, DANGER }

data class AlertItem(
    val hiveName: String,
    val message: String,
    val type: AlertType
)

class AlertAdapter : ListAdapter<AlertItem, AlertAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<AlertItem>() {
            override fun areItemsTheSame(a: AlertItem, b: AlertItem) = a.hiveName == b.hiveName
            override fun areContentsTheSame(a: AlertItem, b: AlertItem) = a == b
        }
    }

    inner class ViewHolder(val binding: ItemAlertBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemAlertBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tvAlertHive.text = item.hiveName
        holder.binding.tvAlertMessage.text = item.message
    }
}
