package com.madhumarga.app.ui.inspection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhumarga.app.data.model.Inspection
import com.madhumarga.app.databinding.ItemInspectionBinding
import java.text.SimpleDateFormat
import java.util.*

class InspectionAdapter(
    private val onDelete: (Inspection) -> Unit
) : ListAdapter<Inspection, InspectionAdapter.ViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Inspection>() {
            override fun areItemsTheSame(a: Inspection, b: Inspection) = a.id == b.id
            override fun areContentsTheSame(a: Inspection, b: Inspection) = a == b
        }
    }

    inner class ViewHolder(val binding: ItemInspectionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemInspectionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val insp = getItem(position)
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        holder.binding.apply {
            tvHiveName.text = insp.hiveName
            tvDate.text = sdf.format(Date(insp.inspectionDate))
            tvQueenSeen.text = if (insp.queenSeen) "👑 Queen seen" else "❓ Queen not seen"
            tvHoneyFlow.text = "Honey flow: ${insp.honeyFlow}"
            tvActivityLevel.text = "Activity: ${insp.activityLevel}"
            if (insp.activityLevel == "Low") {
                tvActivityLevel.setTextColor(root.context.getColor(android.R.color.holo_red_dark))
                tvInterventionAlert.visibility = android.view.View.VISIBLE
            } else {
                tvActivityLevel.setTextColor(root.context.getColor(android.R.color.holo_green_dark))
                tvInterventionAlert.visibility = android.view.View.GONE
            }
            tvPests.text = if (insp.pestsSeen) "⚠️ Pests: ${insp.pestType.ifEmpty { "Unspecified" }}" else "✅ No pests"
            if (insp.notes.isNotEmpty()) {
                tvNotes.text = "Notes: ${insp.notes}"
                tvNotes.visibility = android.view.View.VISIBLE
            } else {
                tvNotes.visibility = android.view.View.GONE
            }
            btnDelete.setOnClickListener { onDelete(insp) }
        }
    }
}
