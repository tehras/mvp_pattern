package com.github.tehras.mvppattern.network

import io.reactivex.Completable

/**
 * Used to determine whether you have network connection or not
 */
interface NetworkInteractor {
    fun hasNetworkConnection(): Boolean
    fun hasNetworkConnectionCompletable(): Completable
    class NetworkUnavailableException : Throwable("No network available!")
}