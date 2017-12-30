package com.github.tehras.mpvlibrary

import com.github.tehras.mpvlibrary.data.cache.CacheModule
import com.github.tehras.mpvlibrary.data.remote.MyServiceModule
import com.github.tehras.mpvlibrary.ui.home.HomeComponent
import com.github.tehras.mpvlibrary.ui.home.HomeModule
import com.github.tehras.mvppattern.network.NetworkModule
import com.github.tehras.mvppattern.rx.MvpComponent
import com.github.tehras.mvppattern.rx.MvpModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MvpModule::class, MyServiceModule::class, NetworkModule::class, CacheModule::class])
interface AppComponent : MvpComponent {

    // Every screen is its own submodule of the graph and must be added here.
    // fun plus(module: Module): ModuleComponent

    fun plus(module: HomeModule): HomeComponent

    fun injectTo(app: Application)
}