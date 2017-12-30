package com.github.tehras.mpvlibrary

import com.github.tehras.mvppattern.MvpApplication

class Application : MvpApplication<AppComponent>() {
    override fun injectTo(component: AppComponent) {
        component.injectTo(this)
    }

    override fun getComponent(): AppComponent {
        return DaggerAppComponent.builder().build()
    }
}