package com.github.tehras.mvppattern.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInteractorImpl @Inject constructor(private val connectivityManager:
                                                ConnectivityManager) : NetworkInteractor {

    @SuppressLint("MissingPermission")
    override fun hasNetworkConnection(): Boolean = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false

    override fun hasNetworkConnectionCompletable(): Completable {
        return if (hasNetworkConnection()) {
            Completable.complete()
        } else {
            Completable.error { NetworkInteractor.NetworkUnavailableException() }
        }
    }

}