package com.example.carinfo_test.data.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CarInfoAPI {

    companion object {
        private const val PROD = "cuvora.com"
        const val CONNECT_TIMEOUT = 15
        const val READ_TIMEOUT = 15
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.NONE
    }

    fun setHttpLogging(enabled: Boolean) {
        logging.level =
            if (enabled)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
    }

    fun getHttpLogging(): Boolean = when (logging.level) {
        HttpLoggingInterceptor.Level.BODY -> true
        else -> false
    }

    private val clientInterceptor = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()

    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .client(clientInterceptor)
        .baseUrl("https://$PROD/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val api: ApiCalls = retrofit.create(
        ApiCalls::class.java
    )

}