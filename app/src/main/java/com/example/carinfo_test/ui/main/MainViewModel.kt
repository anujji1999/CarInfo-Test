package com.example.carinfo_test.ui.main

import androidx.lifecycle.viewModelScope
import com.example.carinfo_test.data.networking.ResultWrapper
import com.example.carinfo_test.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: MainRepository
) : BaseViewModel() {

    fun updateList() {
        viewModelScope.launch(Dispatchers.IO){
            when (val response = repo.updateCarList()) {
                is ResultWrapper.GenericError -> setError(response.error)
                is ResultWrapper.Success -> {
                    with(response.value) {
                        if (isSuccessful) {
                            repo.saveCarList(response.value.body())
                        } else {
                            setError(response.value.message())
                        }
                    }
                }
            }
        }
    }

    fun getList() = repo.getList()

}