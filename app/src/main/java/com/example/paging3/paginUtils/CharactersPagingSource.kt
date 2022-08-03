package com.example.paging3.paginUtils

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.model.Results
import com.example.paging3.network.RetrofitApis

/**
 * This class will act as a REPOSITORY which will talk to VIEW-MODEL
 * */

class CharactersPagingSource(private val apiRequest: RetrofitApis) : PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val nextIndex = params.key ?: 1    //?: this is elvis operator used for null safety.
            val response = apiRequest.getDataFromApi(nextIndex)
            var nextPageNumber: Int ?= null

            //This is used to auto increment page number and make request.
            //If you are being provided by page number then no need to do this
            if(response.info.next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)
        }catch (e: Exception) {
            /**
             * One of the main benefit of PAGING 3 is that you can handle errors on Paging level
             * */
            LoadResult.Error(e)
        }
    }
}