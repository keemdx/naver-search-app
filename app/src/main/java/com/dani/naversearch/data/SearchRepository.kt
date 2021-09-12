package com.dani.naversearch.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dani.naversearch.BuildConfig
import com.dani.naversearch.api.NaverAPI
import kotlinx.coroutines.flow.Flow
import com.dani.naversearch.util.errorLog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.Query

class SearchRepository {

    private val BASE_URL = "https://openapi.naver.com/"

    private val logger = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val headerInterceptor = Interceptor {
        val request = it.request()
            .newBuilder()
            .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SECRET)
            .build()
        return@Interceptor it.proceed(request)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(NaverAPI::class.java)

    fun getSearch(type: String, query: String, display: Int?, start: Int?): Call<ResultGetSearch> =
        api.getSearch(type, query, display, start)

}

