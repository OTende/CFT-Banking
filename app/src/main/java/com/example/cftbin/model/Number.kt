package com.example.cftbin.model

import androidx.room.Entity

@Entity
class Number(
    val length: Int,
    val luhn: Boolean
)