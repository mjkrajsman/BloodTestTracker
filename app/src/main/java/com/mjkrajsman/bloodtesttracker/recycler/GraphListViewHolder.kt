package com.mjkrajsman.bloodtesttracker.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import kotlinx.android.synthetic.main.graph_list_item.view.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class GraphListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val graphListParameterTextView = itemView.graph_list_parameter_text
    private val graphItemLayout = itemView.graph_list_data_layout

    fun bindItem(string: String, onItemClicked: (String) -> Unit) {

        //---===bind data from data set to RecyclerView list elements===---
        graphListParameterTextView.text = BloodTestItem.getAbbreviation(string)

        //---===onClick action===---
        graphItemLayout.setOnClickListener {
            onItemClicked(string)
        }

    }
}