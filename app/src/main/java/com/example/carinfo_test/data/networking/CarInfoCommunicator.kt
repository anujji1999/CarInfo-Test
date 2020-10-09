package com.example.carinfo_test.data.networking

object CarInfoCommunicator {
    private var clients: CarInfoAPI = CarInfoAPI()

    val api get() = clients.api

}