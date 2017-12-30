package com.github.tehras.mvppattern.base

interface Presenter<in V : MvpView> {

    fun bindView(view: V)

    fun unbindView()

    fun onDestroy()
}