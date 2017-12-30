package com.github.tehras.mpvlibrary.data.remote

import com.github.tehras.mpvlibrary.data.models.PlaceHolder
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MyService {

    @GET("/posts/{post}")
    fun placeHolder(@Path("post") post: Int): Single<PlaceHolder>
}