package com.example.carinfo_test.di

import androidx.room.Room
import com.example.carinfo_test.data.database.AppDatabase
import com.example.carinfo_test.data.database.CarInfoModel
import com.example.carinfo_test.ui.carDetail.CarDetailRepository
import com.example.carinfo_test.ui.carDetail.CarDetailViewModel
import com.example.carinfo_test.ui.main.MainRepository
import com.example.carinfo_test.ui.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CarDetailViewModel(get()) }

    single { MainRepository(get()) }
    single { CarDetailRepository(get()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "car-info-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        val database: AppDatabase = get()
        database.carInfoDao()
    }
}
