package com.example.cftbin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cftbin.databinding.HistoryItemBinding
import com.example.cftbin.model.User

class HistoryAdapter : ListAdapter<User, HistoryAdapter.HistoryViewHolder>(HistoryItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : HistoryViewHolder = HistoryViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoryItemBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }

        fun bind(item: User) {
            binding.userInfo.text = item.user_id.toString()
            binding.cardView.setOnClickListener {
                binding.extraInfo.isVisible = !binding.extraInfo.isVisible
                val info = "Тип: ${item.type}\nБренд: ${item.brand}"
                binding.extraInfo.text = info
            }
        }
    }
}