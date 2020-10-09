package com.example.carinfo_test.ui.carDetail

import com.example.carinfo_test.data.database.dao.CarInfoDao

class CarDetailRepository(
    private val carInfoDao: CarInfoDao
) {

    fun getCar(id: Int) = carInfoDao.getCar(id)

}
