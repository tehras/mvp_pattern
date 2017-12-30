package com.github.tehras.mpvlibrary.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.github.tehras.mpvlibrary.AppComponent
import com.github.tehras.mpvlibrary.R
import com.github.tehras.mvppattern.base.PresenterActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : PresenterActivity<HomeView, HomePresenter, AppComponent>(), HomeView {
    override fun injectDependencies(graph: AppComponent) {
        graph.plus(HomeModule(this)).injectTo(this)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
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
}
