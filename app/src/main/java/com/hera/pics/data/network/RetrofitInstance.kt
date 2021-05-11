package com.hera.pics.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://dev-tasks.alef.im")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ImageApi by lazy {
        retrofit.create(ImageApi::class.java)
    }
}