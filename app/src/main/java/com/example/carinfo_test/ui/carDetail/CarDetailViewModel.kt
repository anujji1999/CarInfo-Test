package com.example.carinfo_test.ui.carDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.carinfo_test.utils.BaseViewModel

class CarDetailViewModel(
    private val repo: CarDetailRepository
) : BaseViewModel() {

    val carId = MutableLiveData<Int>()

    //Will trigger this function whenever the carId live data changes so that we don't need to observe it.
    fun getCar() = Transformations.switchMap(carId){
        repo.getCar(it)
    }

}