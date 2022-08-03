package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.adapters.CharactersAdapter
import com.example.paging3.adapters.LoadMoreAdapter
import com.example.paging3.paginUtils.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    lateinit var charactersAdapter: CharactersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {

        rv_items.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            charactersAdapter = CharactersAdapter()
            adapter = charactersAdapter.withLoadStateFooter(LoadMoreAdapter())
        }
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                charactersAdapter.submitData(it)
            }
        }
    }
}