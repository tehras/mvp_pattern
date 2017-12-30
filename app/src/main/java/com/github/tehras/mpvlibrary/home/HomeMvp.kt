package com.github.tehras.mpvlibrary.home

import com.github.tehras.mvppattern.base.MvpView
import com.github.tehras.mvppattern.base.Presenter
import com.github.tehras.mvppattern.base.rx.RxPresenter
import javax.inject.Inject

interface HomeView : MvpView

interface HomePresenter : Presenter<HomeView>

class HomePresenterImpl @Inject constructor() : RxPresenter<HomeView>(), HomePresenter
