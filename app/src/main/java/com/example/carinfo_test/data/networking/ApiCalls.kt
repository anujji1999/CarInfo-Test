package com.example.carinfo_test.data.networking

import com.example.carinfo_test.data.networking.Models.CarList
import retrofit2.Response
import retrofit2.http.GET

interface ApiCalls {

    @GET("car/utils/trending/celebs")
    suspend fun getCelebs(): Response<CarList>

}