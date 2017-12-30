package com.github.tehras.mpvlibrary.data.remote

import com.github.tehras.mvppattern.remote.ApiModule
import dagger.Module

@Module
class MyServiceModule : ApiModule<MyService>() {
    override fun baseUrl(): String = "https://jsonplaceholder.typicode.com"
    override fun apiService(): Class<MyService> = MyService::class.java

}