package com.github.tehras.mvppattern.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.github.tehras.mvppattern.MvpApplication
import com.github.tehras.mvppattern.rx.MvpComponent

abstract class BaseFragment<in M : MvpComponent> : Fragment() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectDependencies(MvpApplication.graph())
    }

    abstract fun injectDependencies(graph: M)
}