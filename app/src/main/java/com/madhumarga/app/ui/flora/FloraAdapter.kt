package com.madhumarga.app.ui.flora

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhumarga.app.data.model.Flora
import com.madhumarga.app.databinding.ItemFloraBinding

class FloraAdapter(
    private val onDelete: (Flora) -> Unit
) : ListAdapter<Flora, FloraAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Flora>() {
            override fun areItemsTheSame(a: Flora, b: Flora) = a.id == b.id
            override fun areContentsTheSame(a: Flora, b: Flora) = a == b
        }
    }

    inner class ViewHolder(val binding: ItemFloraBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemFloraBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val flora = getItem(position)
        holder.binding.apply {
            tvFlowerName.text = "🌸 ${flora.flowerName}"
            tvBloomingSeason.text = "🗓️ Blooms: ${flora.bloomingSeason}"
            tvNectarRating.text = "Nectar: ${"🍯".repeat(flora.nectarRating)}"
            if (flora.distanceKm > 0) {
                tvDistance.text = "📍 ${flora.distanceKm} km away"
                tvDistance.visibility = android.view.View.VISIBLE
            } else {
                tvDistance.visibility = android.view.View.GONE
            }
            if (flora.notes.isNotEmpty()) {
                tvNotes.text = flora.notes
                tvNotes.visibility = android.view.View.VISIBLE
            } else {
                tvNotes.visibility = android.view.View.GONE
            }
            btnDelete.setOnClickListener { onDelete(flora) }
        }
    }
}
