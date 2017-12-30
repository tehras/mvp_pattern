package com.github.tehras.mpvlibrary.ui.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.github.tehras.mpvlibrary.AppComponent
import com.github.tehras.mpvlibrary.R
import com.github.tehras.mpvlibrary.data.models.PlaceHolder
import com.github.tehras.mvppattern.base.PresenterActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_placeholder_display.*

class HomeActivity : PresenterActivity<HomeView, HomePresenter, AppComponent>(), HomeView {
    override fun displayPlaceholder(placeHolder: PlaceHolder) {
        placeholder_loading.visibility = View.GONE
        placeholder_loading.hide()

        placeholder_title.text = placeHolder.title
        placeholder_body.text = placeHolder.body
    }

    override fun displayLoading() {
        placeholder_loading.visibility = View.VISIBLE
        placeholder_loading.show()
    }

    override fun displayError() {
        placeholder_loading.visibility = View.GONE
        placeholder_loading.hide()
    }

    override fun injectDependencies(graph: AppComponent) {
        graph.plus(HomeModule(this)).injectTo(this)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_first -> {
                presenter.fetchPlaceHolder(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_second -> {
                presenter.fetchPlaceHolder(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_third -> {
                presenter.fetchPlaceHolder(3)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onPresenterProvided(presenter: HomePresenter) {
        super.onPresenterProvided(presenter)

        if (freshStart)
            presenter.fetchPlaceHolder(1)
    }
}
