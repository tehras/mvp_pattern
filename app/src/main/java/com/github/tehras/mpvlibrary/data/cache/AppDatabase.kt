package com.github.tehras.mpvlibrary.data.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.tehras.mpvlibrary.data.models.PlaceHolder


@Database(entities = [(PlaceHolder::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun placeHolderDao(): PlaceHolderDao
}
