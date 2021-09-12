package com.dani.naversearch.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dani.naversearch.R
import com.dani.naversearch.enums.PageType

class BottomNavigationViewModel : ViewModel() {
    private val _currentPageType = MutableLiveData(PageType.BLOG)
    val currentPageType: LiveData<PageType> = _currentPageType

    fun setCurrentPage(menuItemId: Int): Boolean {
        val pageType = getPageType(menuItemId)
        changeCurrentPage(pageType)
        return true
    }

    private fun getPageType(menuItemId: Int): PageType {
        return when (menuItemId) {
            R.id.action_blog_search -> PageType.BLOG
            R.id.action_cafe_search -> PageType.CAFE
            R.id.action_image_search -> PageType.IMAGE
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    private fun changeCurrentPage(pageType: PageType) {
        if (currentPageType.value == pageType) return
        _currentPageType.value = pageType
    }
}


