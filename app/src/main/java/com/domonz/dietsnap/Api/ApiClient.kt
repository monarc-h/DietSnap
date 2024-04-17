package com.domonz.dietsnap.Api

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@SuppressLint("LogNotTimber")
object ApiClient {

    val BASE_URL = "http://52.25.229.242:8000"

    private var retrofit: Retrofit? = null

    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor {message -> Log.d("API_RESPONSE",message)}
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }


}