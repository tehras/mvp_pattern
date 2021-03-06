package com.github.tehras.mpvlibrary.ui.home

import android.support.v7.app.AppCompatActivity
import com.github.tehras.mpvlibrary.data.home.HomeDataHandler
import com.github.tehras.mpvlibrary.data.home.HomeDataHandlerImpl
import com.github.tehras.mvppattern.ActivityScope
import com.github.tehras.mvppattern.base.di.ActivityModule
import dagger.Module
import dagger.Provides
import dagger.Subcomponent


@Module
class HomeModule(activity: AppCompatActivity) : ActivityModule(activity) {

    @Provides
    @ActivityScope
    fun provideHomePresenter(presenter: HomePresenterImpl): HomePresenter = presenter


    @Provides
    @ActivityScope
    fun providesHomeDataProvider(homeDataHandler: HomeDataHandlerImpl): HomeDataHandler = homeDataHandler
}

@ActivityScope
@Subcomponent(modules = [(HomeModule::class)])
interface HomeComponent {

    fun injectTo(activity: HomeActivity)
}