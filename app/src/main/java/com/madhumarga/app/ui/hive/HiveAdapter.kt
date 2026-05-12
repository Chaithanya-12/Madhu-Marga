package com.madhumarga.app.ui.hive

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhumarga.app.data.model.Hive
import com.madhumarga.app.databinding.ItemHiveBinding

class HiveAdapter(
    private val onEdit: (Hive) -> Unit,
    private val onDelete: (Hive) -> Unit
) : ListAdapter<Hive, HiveAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Hive>() {
            override fun areItemsTheSame(a: Hive, b: Hive) = a.id == b.id
            override fun areContentsTheSame(a: Hive, b: Hive) = a == b
        }
    }

    inner class ViewHolder(val binding: ItemHiveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemHiveBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hive = getItem(position)
        holder.binding.apply {
            tvHiveName.text = hive.name
            tvHiveLocation.text = "📍 ${hive.location}"
            tvActivityLevel.text = "Activity: ${hive.activityLevel}"
            tvQueenStatus.text = if (hive.queenPresent) "👑 Queen Present" else "⚠️ Queen Absent"
            if (hive.activityLevel == "Low") {
                tvActivityLevel.setTextColor(root.context.getColor(android.R.color.holo_red_dark))
            } else {
                tvActivityLevel.setTextColor(root.context.getColor(android.R.color.holo_green_dark))
            }
            btnEdit.setOnClickListener { onEdit(hive) }
            btnDelete.setOnClickListener { onDelete(hive) }
            if (!hive.notes.isNullOrBlank()) {
                tvNotes.visibility = android.view.View.VISIBLE
                tvNotes.text = "📝 ${hive.notes}"
            } else {
                tvNotes.visibility = android.view.View.GONE
            }
        }
    }
}
