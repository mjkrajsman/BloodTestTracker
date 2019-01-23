package com.mjkrajsman.bloodtesttracker.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.R

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientAdapter(private val items: List<PatientItem>, private val onItemClicked: (PatientItem) -> Unit) :
    RecyclerView.Adapter<PatientViewHolder>() {

    //---===RecyclerView===---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_list_item, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bindItem(items[position], onItemClicked)
    }

    override fun getItemCount() = items.size

}


