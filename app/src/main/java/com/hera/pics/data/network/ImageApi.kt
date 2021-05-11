package com.hera.pics.data.network

import retrofit2.http.GET

interface ImageApi {

    @GET("task-m-001/list.php")
    suspend fun getImageUri(): Array<String>
}