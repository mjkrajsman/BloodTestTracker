package com.mjkrajsman.bloodtesttracker.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.mjkrajsman.bloodtesttracker.R
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import kotlinx.android.synthetic.main.blood_test_list_item.view.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val testDateTextView = itemView.blood_test_date_text
    private val bloodTestItemLayout = itemView.blood_test_item_layout

    fun bindItem(bloodTestItem: BloodTestItem, onItemClicked: (BloodTestItem) -> Unit) {

        //---===bind data from data set to RecyclerView list elements===---
        //birth date and age
        val testDateString = itemView.context.getString(
            R.string.date_template, bloodTestItem.testDate.toLocalDate().toString())
        testDateTextView.text = testDateString

        //---===onClick action===---
        bloodTestItemLayout.setOnClickListener {
            onItemClicked(bloodTestItem)
        }
    }

}