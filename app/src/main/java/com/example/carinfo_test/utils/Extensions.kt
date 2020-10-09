package com.example.carinfo_test.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


fun AppCompatActivity.setToolbar(
    toolbar: Toolbar,
    hasUpEnabled: Boolean = true,
    homeButtonEnabled: Boolean = true,
    title: String = "",
    show: Boolean = true,
    @DrawableRes indicator: Int = 0
) {
    setSupportActionBar(toolbar)
    if (show) {
        if (title.isNotEmpty())
            supportActionBar?.title = title
        if (indicator != 0)
            supportActionBar?.setHomeAsUpIndicator(indicator)
        supportActionBar?.setDisplayHomeAsUpEnabled(hasUpEnabled)
        supportActionBar?.setHomeButtonEnabled(homeButtonEnabled)
        supportActionBar?.show()
    } else supportActionBar?.hide()
}

fun RecyclerView.setRv(
    activity: Context,
    listAdapter: ListAdapter<out Any, out RecyclerView.ViewHolder>,
    orientation: Int = RecyclerView.VERTICAL,
    reverse: Boolean = false
) {
    layoutManager = LinearLayoutManager(activity, orientation, reverse)
    adapter = listAdapter
}

fun View.showSnackbar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT,
    anchorView: BottomNavigationView? = null,
    action: Boolean = false,
    actionText: String = "Retry",
    callback: () -> Unit = { }
): Snackbar {
    val snackBarView = Snackbar.make(this, message, length)
    val params = snackBarView.view.layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        params.leftMargin,
        params.topMargin,
        params.rightMargin,
        params.bottomMargin + 100
    )
    snackBarView.animationMode = Snackbar.ANIMATION_MODE_SLIDE

    snackBarView.view.layoutParams = params
    if (anchorView != null) {
        snackBarView.anchorView = anchorView
    }
    if (action)
        snackBarView.setAction(actionText) {
            callback()
        }
    snackBarView.show()
    return snackBarView
}

fun ImageView.loadImage(url: String, originalSize: Boolean = false){
    val glide = Glide.with(context)
        .load(url)
    if (originalSize){
        glide.override(SIZE_ORIGINAL)
    }
    glide.into(this)
}
