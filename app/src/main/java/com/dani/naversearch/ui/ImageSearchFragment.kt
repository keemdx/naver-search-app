package com.dani.naversearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dani.naversearch.adapters.ImageListAdapter
import com.dani.naversearch.api.NaverAPI
import com.dani.naversearch.data.ResultGetSearch
import com.dani.naversearch.databinding.FragmentImageSearchBinding
import com.dani.naversearch.databinding.LayoutSearchViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageSearchFragment : Fragment() {
    private lateinit var binding: FragmentImageSearchBinding
    private lateinit var searchLayoutBinding: LayoutSearchViewBinding
    private lateinit var searchKeyword: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<ImageListAdapter.ViewHolder>
    private val api = NaverAPI.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageSearchBinding.inflate(inflater, container, false)
        searchLayoutBinding = binding.searchView

        recyclerView = binding.rvImageList
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        recyclerView.layoutManager = layoutManager

        searchLayoutBinding.ivSearch.setOnClickListener {
            searchKeyword = searchLayoutBinding.etSearch.text.toString()
            initNavigationBar(searchKeyword)
        }

        return binding.root
    }

    private fun initNavigationBar(searchKeyword: String) {

        api.getSearch("image", searchKeyword).enqueue(object : Callback<ResultGetSearch> {
            override fun onResponse(
                call: Call<ResultGetSearch>,
                response: Response<ResultGetSearch>
            ) {
                var result = response.body()
                var itemResult = result!!.items
                adapter = ImageListAdapter(itemResult)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ResultGetSearch>, t: Throwable) {
            }
        })

    }
}