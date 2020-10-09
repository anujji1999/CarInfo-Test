package com.example.carinfo_test.ui.main

import android.app.ActivityOptions
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.view.isVisible
import com.example.carinfo_test.R
import com.example.carinfo_test.ui.carDetail.CarDetailActivity
import com.example.carinfo_test.utils.setRv
import com.example.carinfo_test.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by stateViewModel()
    private val adapter = CarListAdapter {item, view->
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            view,
            getString(R.string.transitionName)
        )
        startActivity(CarDetailActivity.getInstance(applicationContext, item.id), options.toBundle())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Material Motion animation
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false

        setContentView(R.layout.activity_main)
        carRv.setRv(applicationContext, adapter)

        //Will handle the orientation change. We will not call the api again
        // if the data is fetched properly during orientation change
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