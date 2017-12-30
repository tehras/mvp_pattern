package com.github.tehras.mpvlibrary

import com.github.tehras.mpvlibrary.data.cache.CacheModule
import com.github.tehras.mvppattern.MvpApplication
import com.github.tehras.mvppattern.rx.MvpModule

class Application : MvpApplication<AppComponent>() {
    override fun injectTo(component: AppComponent) {
        component.injectTo(this)
    }

    override fun getComponent(): AppComponent {
        return DaggerAppComponent
                .builder()
                .mvpModule(MvpModule(this))
                .cacheModule(CacheModule(this))
                .build()
    }
}