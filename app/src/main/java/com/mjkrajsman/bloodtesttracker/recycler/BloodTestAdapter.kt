package com.mjkrajsman.bloodtesttracker.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mjkrajsman.bloodtesttracker.R
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: create adapter template?
class BloodTestAdapter internal constructor (private val onItemClicked: (BloodTestItem) -> Unit, context: Context) :
        RecyclerView.Adapter<BloodTestViewHolder>() {
        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var bloodTests = emptyList<BloodTest>()

        //---===RecyclerView===---
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodTestViewHolder {
            val view = inflater.inflate(R.layout.blood_test_list_item, parent, false)
            return BloodTestViewHolder(view)
        }

        override fun onBindViewHolder(holder: BloodTestViewHolder, position: Int) {
           holder.bindItem(BloodTestItem(bloodTests[position]), onItemClicked)
        }

        internal fun setItems(bloodTests: List<BloodTest>) {
            Log.d("BloodTestAdapter","setItems")
            this.bloodTests = bloodTests
            notifyDataSetChanged()
        }

        override fun getItemCount() = bloodTests.size
    }