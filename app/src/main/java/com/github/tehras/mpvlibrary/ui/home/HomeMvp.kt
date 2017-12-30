package com.github.tehras.mpvlibrary.ui.home

import com.github.tehras.mpvlibrary.data.home.HomeDataHandler
import com.github.tehras.mpvlibrary.data.models.PlaceHolder
import com.github.tehras.mvppattern.base.MvpView
import com.github.tehras.mvppattern.base.Presenter
import com.github.tehras.mvppattern.base.rx.RxPresenter
import io.reactivex.disposables.Disposable
import javax.inject.Inject

interface HomeView : MvpView {
    fun displayPlaceholder(placeHolder: PlaceHolder)
    fun displayLoading()
    fun displayError()
}

interface HomePresenter : Presenter<HomeView> {
    fun fetchPlaceHolder(id: Int)
}

class HomePresenterImpl @Inject constructor(private val homeDataHandler: HomeDataHandler) : RxPresenter<HomeView>(), HomePresenter {
    private var placeHolderSubscribtion: Disposable? = null

    override fun fetchPlaceHolder(id: Int) {

        if (placeHolderSubscribtion?.isDisposed != true) {
            view?.displayLoading()

            val sub = homeDataHandler.getPlaceHolder(id).subscribe({ data ->
                view?.displayPlaceholder(data)
            }, {
                view?.displayError()
            })


            addSubscription(sub)
        }
    }
}
