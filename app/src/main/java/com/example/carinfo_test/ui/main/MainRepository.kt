package com.example.carinfo_test.ui.main

import com.example.carinfo_test.data.database.CarInfoModel
import com.example.carinfo_test.data.database.dao.CarInfoDao
import com.example.carinfo_test.data.networking.CarInfoCommunicator
import com.example.carinfo_test.data.networking.Models.CarList
import com.example.carinfo_test.data.networking.safeApiCall

class MainRepository(
    private val carInfoDao: CarInfoDao
) {

    suspend fun updateCarList() = safeApiCall { CarInfoCommunicator.api.getCelebs() }

    suspend fun saveCarList(item: CarList?) {
        if (item == null)
            return

        val carInfoModel = item.data.list.map {
            val carNumber = it.registrationNumber
            val imageUrl = it.imageURL
            val shareText = it.vehicleDetails[0].shareText
            var ownerName = ""
            var ownerShip = ""
            var financerName = ""
            var makerModel = ""
            var registrationDate = ""
            var vehicleAge = ""
            var insuranceExp = ""
            var fuelType = ""
            var vehicleClass = ""
            it.vehicleDetails[0].info.forEach { info ->
                when (info.key) {
                    "Owner Name" -> ownerName = info.value
                    "Ownership" -> ownerShip = info.value
                    "Financer's Name (HP)" -> financerName = info.value
                    "Maker Model" -> makerModel = info.value
                    "Registration Date" -> registrationDate = info.value
                    "Vehicle Age" -> vehicleAge = info.value
                    "Insurance Expiry (Tentative)" -> insuranceExp = info.value
                    "Fuel Type" -> fuelType = info.value
                    "Vehicle Class" -> vehicleClass = info.value
                }
            }
            CarInfoModel(
                0,
                carNumber,
                imageUrl,
                shareText,
                ownerName,
                ownerShip,
                vehicleClass,
                financerName,
                makerModel,
                registrationDate,
                vehicleAge,
                insuranceExp,
                fuelType
            )
        }

        carInfoDao.deleteData()
        carInfoDao.insertAll(carInfoModel)
    }

    fun getList() = carInfoDao.getList()

}
