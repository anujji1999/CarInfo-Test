package com.example.carinfo_test.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.carinfo_test.data.database.dao.CarInfoDao

@Database(
    entities = [CarInfoModel::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun carInfoDao(): CarInfoDao

}