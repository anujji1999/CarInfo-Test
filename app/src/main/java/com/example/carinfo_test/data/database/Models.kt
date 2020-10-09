package com.example.carinfo_test.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarInfoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val carNumber: String,
    val imageUrl: String,
    val shareText: String,
    val ownerName: String,
    val ownerShip: String,
    val vehicleClass: String,
    val financeName: String,
    val makerModel: String,
    val registrationDate: String,
    val vehicleAge: String,
    val insuranceExp: String,
    val fuelType: String
)