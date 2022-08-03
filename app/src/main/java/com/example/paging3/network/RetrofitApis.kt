package com.example.paging3.network

import com.example.paging3.model.DataParent
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApis {

    @GET("character")
    suspend fun getDataFromApi(@Query("page") pageNumber: Int) : DataParent
}