package com.example.cftbin.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.cftbin.model.User

class HistoryItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.user_id == newItem.user_id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}