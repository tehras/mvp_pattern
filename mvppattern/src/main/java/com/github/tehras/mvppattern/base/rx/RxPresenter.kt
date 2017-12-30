package com.github.tehras.mvppattern.base.rx

import android.app.Activity
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import com.github.tehras.mvppattern.MvpApplication
import com.github.tehras.mvppattern.base.AbstractPresenter
import com.github.tehras.mvppattern.base.MvpView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

@Suppress("unused")
abstract class RxPresenter<V : MvpView> : AbstractPresenter<V>() {

    protected val subscriptions: CompositeDisposable = CompositeDisposable()
    protected val busSubscriptions: CompositeDisposable = CompositeDisposable()
    private val viewState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        viewState.onNext(false)
    }

    @CallSuper
    override fun bindView(view: V) {
        super.bindView(view)

        viewState.onNext(true)
    }

    @CallSuper
    override fun unbindView() {
        clearSubscriptions()

        super.unbindView()
        viewState.onNext(false)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        viewState.onComplete()

        //we have to unbind, because we don't want the old presenter to retain anything
//        clearSubscriptions()
    }

    private fun clearSubscriptions() {
        subscriptions.clear()
        busSubscriptions.clear()
    }

    fun addSubscription(subscription: Disposable) {
        subscriptions.add(subscription)
    }

    fun subscribeToBus(onNext: (t: Any) -> Unit) {
        getApplication()?.bus()?.let {
            busSubscriptions.add(it
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext))
        }
    }

    fun sendToBus(o: Any) {
        getApplication()?.bus()?.send(o)
    }

    private fun getApplication(): MvpApplication<*>? {
        view?.let {
            if (it is Fragment && it.activity?.application is MvpApplication<*>) {
                return it.activity!!.application as MvpApplication<*>
            } else if (it is Activity && it.application is MvpApplication<*>) {
                return it.application as MvpApplication<*>
            }
        }

        return null
    }

}