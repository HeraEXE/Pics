package com.hera.pics.data

import com.hera.pics.data.network.RetrofitInstance

class Repository {

    suspend fun getImageUri(): Array<String> {
        return RetrofitInstance.api.getImageUri()
    }
}