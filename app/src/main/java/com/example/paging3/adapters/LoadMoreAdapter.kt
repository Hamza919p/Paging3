package com.example.paging3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.R

class LoadMoreAdapter : LoadStateAdapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.load_more, parent, false)
        return LoadMoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {
        holder as LoadMoreViewHolder
        holder.loadMore.isVisible = loadState is LoadState.Loading
    }


    private inner class LoadMoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loadMore: ProgressBar = itemView.findViewById(R.id.progress_bar)
    }

}