package com.example.kabaddiarena

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val matchList: List<MatchEntity>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOpponent: TextView = itemView.findViewById(R.id.tvOpponent)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvRaids: TextView = itemView.findViewById(R.id.tvRaids)
        val tvSuccess: TextView = itemView.findViewById(R.id.tvSuccess)
        val tvFailed: TextView = itemView.findViewById(R.id.tvFailed)
        val tvTackles: TextView = itemView.findViewById(R.id.tvTackles)
        val tvResult: TextView = itemView.findViewById(R.id.tvResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matchList[position]

        holder.tvOpponent.text = "vs ${match.opponent}"
        holder.tvDate.text = match.date
        holder.tvRaids.text = "Raids\n${match.points}"
        holder.tvSuccess.text = "Success\n${match.points / 2}"
        holder.tvFailed.text = "Failed\n${match.points / 3}"
        holder.tvTackles.text = "Tackles\n${match.points / 4}"

        if (match.points > 0) {
            holder.tvResult.text = "WON"
            holder.tvResult.setBackgroundColor(Color.parseColor("#28A745"))
        } else {
            holder.tvResult.text = "LOST"
            holder.tvResult.setBackgroundColor(Color.parseColor("#DC3545"))
        }
    }

    override fun getItemCount(): Int = matchList.size
}