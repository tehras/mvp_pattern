package com.github.tehras.mvppattern.remote

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class ApiModule<T> {
    /**
     * Override if 5 seconds is too short
     */
    protected val connectionTimeout = 5000L
    protected var logEnabled = false

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): T = retrofit.create(apiService())

    abstract fun apiService(): Class<T>
    abstract fun baseUrl(): String

    /**
     * Use this to add custom headers
     */
    protected open fun Request.Builder.addHeaders() {}

    @Provides
    @Singleton
    fun provideRetrofit(rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl())
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient())
                .build()
    }

    private fun log(message: String, throwable: Throwable? = null) {
        if (logEnabled) {
            if (throwable != null)
                Log.e("HttpRequest", message, throwable)
            else
                Log.d("HttpRequest", message)
        }
    }

    /**
     * Override for custom actions
     */
    protected open fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor {
            val newRequest = it.request().newBuilder()
                    .apply {
                        this.addHeaders()
                    }
                    .build()

            log("request being sent to ${newRequest.url()}")

            return@addNetworkInterceptor it.proceed(newRequest)
        }
                .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJavaCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
}