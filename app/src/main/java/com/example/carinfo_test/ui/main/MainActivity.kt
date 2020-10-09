package com.example.carinfo_test.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.carinfo_test.R
import com.example.carinfo_test.ui.carDetail.CarDetailActivity
import com.example.carinfo_test.utils.setRv
import com.example.carinfo_test.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by stateViewModel()
    private val adapter = CarListAdapter {
        startActivity(CarDetailActivity.getInstance(applicationContext, it.id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carRv.setRv(applicationContext, adapter)

        if (savedInstanceState == null){
            vm.updateList()
        }

        vm.getList().observe(this){
            shimmerLayout.isVisible = it.isNullOrEmpty()
            carRv.isVisible = !shimmerLayout.isVisible
            adapter.submitList(it)
        }

        vm.errorLiveData.observe(this){
            carRv.showSnackbar(it, action = true, length = Snackbar.LENGTH_INDEFINITE){
                vm.updateList()
            }
        }
    }
}