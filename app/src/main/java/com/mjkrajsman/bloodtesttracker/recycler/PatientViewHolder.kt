package com.mjkrajsman.bloodtesttracker.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.*
import kotlinx.android.synthetic.main.patient_list_item.view.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val nameTextView = itemView.patient_name_text
    private val birthDateAgeTextView = itemView.patient_birth_date_age_text
    private val patientItemLayout = itemView.patient_item_layout
    private val sexImageView = itemView.sex_image


    fun bindItem(patientItem: PatientItem, onItemClicked: (PatientItem) -> Unit) {

        //---===bind data from data set to RecyclerView list elements===---
        //names
        if(patientItem.secondName!=null) {
            nameTextView.text = itemView.context.getString(
                R.string.patient_title_name_template, patientItem.firstName,
                patientItem.secondName, patientItem.surname)
        }else{
            nameTextView.text = itemView.context.getString(
                R.string.patient_title_name_short_template, patientItem.firstName,
                patientItem.surname)
        }
        //birth date and age
        val birthDateAgeString = itemView.context.resources.getQuantityString(R.plurals.patient_birth_date_age_template, patientItem.age,
            patientItem.birthDate.toLocalDate().toString(), patientItem.age)
        birthDateAgeTextView.text = birthDateAgeString
        //sex
        if(patientItem.sex) {
            sexImageView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_male_3_blue))
        }else{
            sexImageView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_female_3_pink))
        }

        //---===onClick action===---
        patientItemLayout.setOnClickListener { onItemClicked(patientItem) }
    }

}