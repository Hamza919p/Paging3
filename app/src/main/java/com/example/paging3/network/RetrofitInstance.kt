package com.example.paging3.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
        fun newInstance() : Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}