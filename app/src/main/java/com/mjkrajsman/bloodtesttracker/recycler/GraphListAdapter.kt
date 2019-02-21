package com.mjkrajsman.bloodtesttracker.recycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mjkrajsman.bloodtesttracker.R

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: sort ascending by surname, then name, then id
class GraphListAdapter internal constructor (private val onItemClicked: (String) -> Unit, context: Context) :
    RecyclerView.Adapter<GraphListViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tests = emptyList<String>()

    //---===RecyclerView===---
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphListViewHolder {
        val view = inflater.inflate(R.layout.graph_list_item, parent, false)
        return GraphListViewHolder(view)
    }

    override fun onBindViewHolder(holderList: GraphListViewHolder, position: Int) {
        holderList.bindItem(tests[position], onItemClicked)
    }

    internal fun setItems(exams: List<String>) {
        this.tests = exams
        notifyDataSetChanged()
    }

    override fun getItemCount() = tests.size
}