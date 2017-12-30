package com.github.tehras.mvppattern.rx

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import com.github.tehras.mvppattern.ApplicationQualifier
import com.github.tehras.mvppattern.MvpApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class MvpModule(private var application: MvpApplication<*>) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideResources(): Resources = application.resources

    @Provides
    @Singleton
    @ApplicationQualifier
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(application)

}