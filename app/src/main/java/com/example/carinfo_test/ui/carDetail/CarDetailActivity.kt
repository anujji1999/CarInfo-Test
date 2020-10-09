package com.example.carinfo_test.ui.carDetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.carinfo_test.R
import com.example.carinfo_test.utils.*
import kotlinx.android.synthetic.main.activity_car_detail.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class CarDetailActivity : AppCompatActivity() {

    companion object{
        fun getInstance(context: Context, carId: Int): Intent{
            return Intent(context, CarDetailActivity::class.java).apply {
                putExtra(CAR_ID, carId)
            }
        }
    }

    val vm: CarDetailViewModel by stateViewModel()

    val carId by lazy {
        intent?.getIntExtra(CAR_ID, 0)?: 0
    }
    lateinit var shareText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)
        setToolbar(toolbar, title = getString(R.string.carInfo), indicator = R.drawable.ic_baseline_arrow_back_24)

        vm.carId.value = carId

        vm.getCar().observe(this){
            carImageView.loadImage(it.imageUrl)
            ownerNameTv.text = it.ownerName
            ownerShipTv.text = it.ownerShip
            fuelTypeTv.text = it.fuelType
            carNumberTv.text = it.carNumber
            vehicleClassTv.text = it.vehicleClass
            vehicleAgeTv.text = it.vehicleAge
            financerTv.text = it.financeName
            modelMakerTv.text = it.makerModel
            registrationTv.text = it.registrationDate
            insuranceTv.text = it.insuranceExp
            shareText = it.shareText
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.car_detail_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                if (::shareText.isInitialized){
                    val intent2 = Intent().apply {
                        action =  Intent.ACTION_SEND
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                    }
                    startActivity(Intent.createChooser(intent2, "Share via"))
                }else{
                    toolbar.showSnackbar(getString(R.string.no_data))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}