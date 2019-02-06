package com.mjkrajsman.bloodtesttracker.recycler

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.R
import com.mjkrajsman.bloodtesttracker.model.Patient

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: sort ascending by surname, then name, then id
class PatientAdapter internal constructor (private val onItemClicked: (PatientItem) -> Unit, context: Context) :
    RecyclerView.Adapter<PatientViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var patients = emptyList<Patient>()

    //---===RecyclerView===---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = inflater.inflate(R.layout.patient_list_item, parent, false)
        return PatientViewHolder(view)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bindItem(PatientItem(patients[position]), onItemClicked)
    }

    internal fun setItems(patients: List<Patient>) {
        Log.d("PatientAdapter","setItems")
        this.patients = patients
        notifyDataSetChanged()
    }

    override fun getItemCount() = patients.size

}


