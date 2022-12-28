package com.example.cftbin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Country(
    @PrimaryKey val country_id: Int,
    val numeric: String?,
    val alpha2: String?,
    @ColumnInfo(name = "country_name") val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
)