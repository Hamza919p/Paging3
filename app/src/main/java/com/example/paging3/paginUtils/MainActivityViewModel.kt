package com.example.paging3.paginUtils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.network.RetrofitApis
import com.example.paging3.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import com.example.paging3.model.Results

class MainActivityViewModel : ViewModel() {

    private val retrofitService = RetrofitInstance.newInstance().create(RetrofitApis::class.java)

    //Flow is a widget of Kotlin couroutines
    fun getListData(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = {CharactersPagingSource(retrofitService)}
        ).flow.cachedIn(viewModelScope)
    }
}