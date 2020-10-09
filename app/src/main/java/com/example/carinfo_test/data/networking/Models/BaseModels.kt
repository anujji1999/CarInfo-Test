package com.example.carinfo_test.data.networking.Models

data class CarList(
    val data: CarData,
    val errors: String?
)

data class CarData(
    val list: List<CarInfo>,
    val lastUpdated: String
)

data class CarInfo(
    val vehicleDetails: List<VehicleDetails>,
    val registrationNumber: String,
    val imageURL: String,
    val brandIcon: String,
    val brandName: String,
    val shareText: String?
)

data class VehicleDetails(
    val info: List<VehicleInfo>,
    val displayName: String,
    val shareText: String
)

data class VehicleInfo(
    val value: String,
    val key: String
)