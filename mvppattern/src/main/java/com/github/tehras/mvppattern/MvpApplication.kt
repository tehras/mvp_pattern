package com.github.tehras.mvppattern

import android.app.Application
import com.github.tehras.mvppattern.base.rx.RxBus
import com.github.tehras.mvppattern.rx.MvpComponent


abstract class MvpApplication<C : MvpComponent> : Application() {
    private lateinit var bus: RxBus

    /**
     * Use this if you want the whole app to know about an event
     */
    fun bus(): RxBus {
        return bus
    }

    companion object {

        private lateinit var component: MvpComponent

        fun <C : MvpComponent> graph(component: C) {
            this.component = component
        }

        @Suppress("UNCHECKED_CAST")
        fun <C : MvpComponent> graph(): C {
            return component as C
        }

    }

    override fun onCreate() {
        super.onCreate()

        initDependencyGraph()


        this.bus = RxBus()

    }

    /**
     * First create AppComponent
     *
     * Then inject the app component
     */
    private fun initDependencyGraph() {
        getComponent().apply {
            graph(this)
            injectTo(this)
        }
    }

    abstract fun injectTo(component: C)

    /**
     * Sample Implementation.
     * Feel free to override
     */
    abstract fun getComponent(): C

}