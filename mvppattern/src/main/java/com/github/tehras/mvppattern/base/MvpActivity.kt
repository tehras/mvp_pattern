package com.github.tehras.mvppattern.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.github.tehras.mvppattern.MvpApplication
import com.github.tehras.mvppattern.rx.MvpComponent

abstract class MvpActivity<in M : MvpComponent> : AppCompatActivity() {
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(MvpApplication.graph())
    }

    //Inject dependencies for Dagger
    abstract fun injectDependencies(graph: M)
}