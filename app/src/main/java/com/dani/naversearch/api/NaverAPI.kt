package com.dani.naversearch.api

import com.dani.naversearch.BuildConfig
import com.dani.naversearch.data.ResultGetSearch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NaverAPI {
    @GET("v1/search/{type}.json")
    fun getSearch(
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<ResultGetSearch>
}
