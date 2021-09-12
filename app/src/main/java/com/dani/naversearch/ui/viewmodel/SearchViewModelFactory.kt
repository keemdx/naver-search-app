package com.dani.naversearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dani.naversearch.data.SearchRepository

class SearchViewModelFactory constructor(private val searchRepository: SearchRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            SearchViewModel(this.searchRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}