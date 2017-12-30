package com.github.tehras.mvppattern.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import javax.inject.Inject
import javax.inject.Provider

abstract class PresenterFragment<V : MvpView, T : Presenter<V>> : BaseFragment(),
        LoaderManager.LoaderCallbacks<T> {

    private val LOADER_ID = 1
    protected lateinit var presenter: T
    protected var isViewBindCompleted: Boolean = false

    // Boolean flag to avoid delivering the Presenter twice. Calling initLoader in onActivityCreated means
    // onLoadFinished will be called twice during configuration change.
    // this is why everyone loves fragments...
    private var delivered = false

    @Inject
    protected lateinit var presenterLoaderProvider: Provider<PresenterLoader<T>>

    override fun onStart() {
        super.onStart()

        initLoader()
    }

    private fun initLoader() {
        loaderManager.initLoader<T>(LOADER_ID, null, this)
    }

    @CallSuper
    protected open fun onPresenterProvided(presenter: T) {
        this.presenter = presenter

        bindView()
    }

    private fun bindView() {
        if (!isViewBindCompleted) {
            presenter.bindView(getViewLayer())
            isViewBindCompleted = true
        }
    }

    @CallSuper
    protected fun onPresenterDestroyed() {
        // Hook for subclasses
    }

    @CallSuper
    override fun onResume() {
        super.onResume()

        bindView()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()

        presenter.unbindView()
        isViewBindCompleted = false
        delivered = false
    }

    protected fun getViewLayer(): V {
        @Suppress("UNCHECKED_CAST")
        return this as V
    }

    override fun onLoadFinished(loader: Loader<T>?, presenter: T) {
        if (!delivered) {
            onPresenterProvided(presenter)
            delivered = true
        }
    }

    override fun onCreateLoader(id: Int, bundle: Bundle?): Loader<T> {
        return presenterLoaderProvider.get()
    }

    override fun onLoaderReset(loader: Loader<T>?) {
        onPresenterDestroyed()
    }
}