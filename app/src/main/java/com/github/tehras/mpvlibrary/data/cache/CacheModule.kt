package com.github.tehras.mpvlibrary.data.cache

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase = Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "sample_cache").build()

    @Provides
    @Singleton
    fun providesPlaceholderDao(appDatabase: AppDatabase): PlaceHolderDao = appDatabase.placeHolderDao()

}