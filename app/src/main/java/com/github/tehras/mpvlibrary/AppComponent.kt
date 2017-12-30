package com.github.tehras.mpvlibrary

import com.github.tehras.mpvlibrary.home.HomeComponent
import com.github.tehras.mpvlibrary.home.HomeModule
import com.github.tehras.mvppattern.rx.MvpComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface AppComponent : MvpComponent {

    // Every screen is its own submodule of the graph and must be added here.
    // fun plus(module: Module): ModuleComponent

    fun plus(module: HomeModule): HomeComponent

    fun injectTo(app: Application)
}