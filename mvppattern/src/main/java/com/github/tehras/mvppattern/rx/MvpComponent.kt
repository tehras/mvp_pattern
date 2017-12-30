package com.github.tehras.mvppattern.rx

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(MvpModule::class)])
interface MvpComponent