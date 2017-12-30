package com.github.tehras.mpvlibrary.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Placeholder")
data class PlaceHolder(val userId: Int,
                       @PrimaryKey val id: Int,
                       val title: String,
                       val body: String)