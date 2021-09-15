package com.dani.naversearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dani.naversearch.data.Item
import com.dani.naversearch.data.ResultGetSearch
import com.dani.naversearch.data.SearchRepository
import com.dani.naversearch.util.errorLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel constructor(private val searchRepository: SearchRepository) : ViewModel() {
    val resultList = MutableLiveData<List<Item>>()

    fun getSearchResult(key: String, searchKeyword: String) {

        searchRepository.getSearch(key, searchKeyword, 20, null)
            .enqueue(object : Callback<ResultGetSearch> {
                override fun onResponse(
                    call: Call<ResultGetSearch>,
                    response: Response<ResultGetSearch>
                ) {
                    val result = response.body()
                    if (result != null && response.isSuccessful) {
                        resultList.postValue(result.items)
                    }
                }

                override fun onFailure(call: Call<ResultGetSearch>, t: Throwable) {
                    errorLog("Fail : $t")
                }
            })
    }

}

