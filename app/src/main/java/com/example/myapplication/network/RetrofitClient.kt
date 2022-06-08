package com.example.myapplication.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var networkService: RetrofitService
//    val URL = ""
    private val client: OkHttpClient
    init {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY}
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
//            .addInterceptor(HeaderInterceptor())
            .connectTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

//    class HeaderInterceptor : Interceptor {
//        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
//            proceed(
//                request()
//                    .newBuilder()
//                    .build()
//            )
//        }
//    }

    val retrofit: Retrofit
        get() = Retrofit.Builder()
//            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    init {
        networkService = retrofit.create(RetrofitService::class.java)
    }
}