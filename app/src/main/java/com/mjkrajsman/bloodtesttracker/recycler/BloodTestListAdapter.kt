package com.mjkrajsman.bloodtesttracker.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjkrajsman.bloodtesttracker.R
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: create adapter template?
class BloodTestListAdapter internal constructor (private val onItemClicked: (BloodTestItem) -> Unit, context: Context) :
        RecyclerView.Adapter<BloodTestListViewHolder>() {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var bloodTestItems = emptyList<BloodTestItem>()

        //---===RecyclerView===---
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodTestListViewHolder {
            val view = inflater.inflate(R.layout.blood_test_list_item, parent, false)
            return BloodTestListViewHolder(view)
        }

        override fun onBindViewHolder(holderList: BloodTestListViewHolder, position: Int) {
           holderList.bindItem(bloodTestItems[position], onItemClicked)
        }

        internal fun setItems(bloodTestItems: List<BloodTestItem>) {
            Log.d("BloodTestListAdapter","setItems")
            this.bloodTestItems = bloodTestItems
            notifyDataSetChanged()
        }

        override fun getItemCount() = bloodTestItems.size
    }