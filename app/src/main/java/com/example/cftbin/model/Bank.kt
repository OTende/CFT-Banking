package com.example.cftbin.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class Bank(
    @PrimaryKey val bank_id: Int,
    @ColumnInfo(name = "bank_name") val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
