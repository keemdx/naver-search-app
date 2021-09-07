package com.dani.naversearch.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dani.naversearch.adapter.ImageListAdapter
import com.dani.naversearch.api.NaverAPI
import com.dani.naversearch.data.Item
import com.dani.naversearch.data.ResultGetSearch
import com.dani.naversearch.databinding.FragmentImageSearchBinding
import com.dani.naversearch.util.KeyboardUtil
import com.dani.naversearch.util.errorLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageSearchFragment : Fragment() {

    private lateinit var binding: FragmentImageSearchBinding
    private val adapter: ImageListAdapter = ImageListAdapter().apply {
        itemClick = object : ImageListAdapter.ItemClick {
            override fun onClick(item: Item) {
                startWebViewActivity(item.link)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageSearchBinding.inflate(inflater, container, false)

        initView()
        binding.searchView.ivSearch.setOnClickListener {
            val searchKeyword = binding.searchView.etSearch.text.toString()
            getSearchResult(searchKeyword)
            KeyboardUtil(requireContext()).hideKeyboard(binding.searchView.etSearch)
        }
        return binding.root
    }

    private fun initView() {
        binding.searchView.etSearch.hint = "Image Search"
        binding.rvImageList.adapter = adapter
    }

    private fun getSearchResult(searchKeyword: String) {
        NaverAPI.naverAPI.getSearch("image", searchKeyword)
            .enqueue(object : Callback<ResultGetSearch> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<ResultGetSearch>,
                    response: Response<ResultGetSearch>
                ) {
                    val result = response.body()
                    if (result != null && response.isSuccessful) {
                        adapter.setItems(result.items)
                    }
                }

                override fun onFailure(call: Call<ResultGetSearch>, t: Throwable) {
                    errorLog("Fail : $t")
                }
            })
    }

    private fun startWebViewActivity(url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }
}