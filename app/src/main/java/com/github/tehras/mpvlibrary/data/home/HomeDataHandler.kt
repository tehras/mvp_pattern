package com.github.tehras.mpvlibrary.data.home

import com.github.tehras.mpvlibrary.data.cache.PlaceHolderDao
import com.github.tehras.mpvlibrary.data.models.PlaceHolder
import com.github.tehras.mpvlibrary.data.remote.MyService
import com.github.tehras.mvppattern.network.NetworkInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface HomeDataHandler {

    fun getPlaceHolder(position: Int): Observable<PlaceHolder>

}

class HomeDataHandlerImpl @Inject constructor(private val placeHolderDao: PlaceHolderDao, private val networkInteractor: NetworkInteractor, private val apiService: MyService) : HomeDataHandler {

    override fun getPlaceHolder(position: Int): Observable<PlaceHolder> {
        return Observable.concat(fetchPlaceHolderFromCache(position), fetchPlaceHolderOverNetwork(position))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun fetchPlaceHolderOverNetwork(position: Int): Observable<PlaceHolder> {
        return networkInteractor.hasNetworkConnectionCompletable()
                .andThen(apiService.placeHolder(position))
                .toObservable()
                .doOnNext {
                    //save to cache when retrieved from the net
                    placeHolderDao.save(it)
                }
    }

    private fun fetchPlaceHolderFromCache(position: Int): Observable<PlaceHolder> {
        return placeHolderDao.get(position)
                .toObservable()
    }


}