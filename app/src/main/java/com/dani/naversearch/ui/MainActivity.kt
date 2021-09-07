package com.dani.naversearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commitNow
import com.dani.naversearch.R
import com.dani.naversearch.databinding.ActivityMainBinding
import com.dani.naversearch.enum.SearchCategory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNavigationBar()
    }

    private fun initNavigationBar() {

        binding.bnvBottomNavi.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_blog_search -> {
                        showSearchTab(SearchCategory.BLOG)
                    }
                    R.id.action_cafe_search -> {
                        showSearchTab(SearchCategory.CAFE)
                    }
                    R.id.action_image_search -> {
                        showSearchTab(SearchCategory.IMAGE)
                    }
                }
                true
            }
            selectedItemId = R.id.action_blog_search
        }
    }

    private fun showSearchTab(category: SearchCategory) {
        val fragment = supportFragmentManager.findFragmentByTag(category.name)
        if (fragment == null) {
            val newFragment = when (category) {
                SearchCategory.BLOG -> BlogSearchFragment()
                SearchCategory.CAFE -> CafeSearchFragment()
                SearchCategory.IMAGE -> ImageSearchFragment()
            }
            supportFragmentManager.commitNow(true) {
                // higher-order function
                // function extension
                replace(binding.fl.id, newFragment, category.name)
            }
        } else {
            supportFragmentManager.commitNow(true) {
                replace(binding.fl.id, fragment, category.name)
            }
        }
    }
}
