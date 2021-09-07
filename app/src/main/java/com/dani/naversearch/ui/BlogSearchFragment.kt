package com.dani.naversearch.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dani.naversearch.adapter.PostListAdapter
import com.dani.naversearch.api.NaverAPI
import com.dani.naversearch.data.Item
import com.dani.naversearch.data.ResultGetSearch
import com.dani.naversearch.databinding.FragmentBlogSearchBinding
import com.dani.naversearch.util.KeyboardUtil
import com.dani.naversearch.util.errorLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogSearchFragment : Fragment() {

    private lateinit var binding: FragmentBlogSearchBinding
    private val adapter: PostListAdapter = PostListAdapter().apply {
        itemClick = object : PostListAdapter.ItemClick {
            override fun onClick(item: Item) {
                startWebViewActivity(item.link)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlogSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        binding.searchView.ivSearch.setOnClickListener {
            val searchKeyword = binding.searchView.etSearch.text.toString()
            getSearchResult(searchKeyword)
            KeyboardUtil(requireContext()).hideKeyboard(binding.searchView.etSearch)
        }
    }

    private fun initView() {
        binding.searchView.etSearch.hint = "Blog Search"
        binding.rvBlogList.adapter = adapter
    }

    private fun getSearchResult(searchKeyword: String) {
        NaverAPI.naverAPI.getSearch("blog", searchKeyword)
            .enqueue(object : Callback<ResultGetSearch> {
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