package com.example.carinfo_test.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.carinfo_test.data.database.CarInfoModel

@Dao
interface CarInfoDao : BaseDao<CarInfoModel> {

    @Query("SELECT * FROM CarInfoModel")
    fun getList(): LiveData<List<CarInfoModel>>

    @Query("SELECT * FROM CarInfoModel WHERE id = :id")
    fun getCar(id: Int): LiveData<CarInfoModel>

    @Query("DELETE FROM CarInfoModel")
    suspend fun deleteData()

}