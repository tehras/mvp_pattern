package com.github.tehras.mvppattern.base.di

import android.support.v4.app.Fragment
import com.github.tehras.mvppattern.FragmentScope
import dagger.Module
import dagger.Provides

@Module
abstract class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment = fragment
}
