package com.example.paging3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R
import com.example.paging3.model.Results

class CharactersAdapter : PagingDataAdapter<Results, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CharactersViewHolder
        holder.text.text = getItem(position)?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_lists, parent, false)
        return CharactersViewHolder(view)
    }

    private inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.tv_text)
    }


    /**
     * DIFF-UTIL differentiate two lists and notify the list excluding the data which is same in both lists
     * "NotifyDataSetChange" do the same thing but it render whole the adapter again
     * "DiffUtil" only change that data which is different from each other
     * */
    private class DiffUtilCallback: androidx.recyclerview.widget.DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species
        }

    }

}