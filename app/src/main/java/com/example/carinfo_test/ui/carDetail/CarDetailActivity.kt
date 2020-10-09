package com.example.carinfo_test.ui.carDetail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import com.example.carinfo_test.R
import com.example.carinfo_test.utils.*
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
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

        //Material Motion Animation
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).transitionName = getString(R.string.transitionName)
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 500L
            scrimColor = Color.TRANSPARENT
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 500L
            scrimColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_car_detail)
        setToolbar(toolbar, indicator = R.drawable.ic_baseline_arrow_back_24)

        //Car Id from intent to viewmodel so that value is assigned and will be used during orientaion change
        vm.carId.value = carId

        vm.getCar().observe(this){
            carImageView.loadImage(it.imageUrl)
            toolbar.title = it.ownerName
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