package com.example.cftbin.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int,
    @Embedded val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded val country: Country?,
    @Embedded val bank: Bank?
)