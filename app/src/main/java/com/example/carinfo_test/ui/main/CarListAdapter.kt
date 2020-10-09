package com.example.carinfo_test.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.carinfo_test.R
import com.example.carinfo_test.data.database.CarInfoModel
import com.example.carinfo_test.utils.loadImage
import kotlinx.android.synthetic.main.car_list_item.view.*

class CarListAdapter(
    private val onItemClick: (item: CarInfoModel) -> Unit
) : ListAdapter<CarInfoModel, CarListAdapter.CarViewHolder>(object : DiffUtil.ItemCallback<CarInfoModel>() {
    override fun areItemsTheSame(oldItem: CarInfoModel, newItem: CarInfoModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CarInfoModel, newItem: CarInfoModel): Boolean {
        return oldItem.carNumber == newItem.carNumber
    }

}) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.car_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }


    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: CarInfoModel, onItemClick: (item: CarInfoModel) -> Unit) {
            with(itemView) {
                carImage.loadImage(item.imageUrl)
                val ownerName = item.ownerName
                carName.text = ownerName
                setOnClickListener {
                    onItemClick(item)
                }
            }
        }

    }


}