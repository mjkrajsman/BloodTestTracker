package com.mjkrajsman.bloodtesttracker.recycler

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.R

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: sort ascending by surname, then name, then id
class PatientListAdapter internal constructor (private val onItemClicked: (PatientItem) -> Unit, context: Context) :
    RecyclerView.Adapter<PatientListViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var patientItems = emptyList<PatientItem>()

    //---===RecyclerView===---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientListViewHolder {
        val view = inflater.inflate(R.layout.patient_list_item, parent, false)
        return PatientListViewHolder(view)
    }

    override fun onBindViewHolder(holderList: PatientListViewHolder, position: Int) {
        holderList.bindItem(patientItems[position], onItemClicked)
    }

    internal fun setItems(patientItems: List<PatientItem>) {
        Log.d("PatientListAdapter","setItems")
        this.patientItems = patientItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = patientItems.size

}


