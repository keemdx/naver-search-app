package com.dani.naversearch.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dani.naversearch.R
import com.dani.naversearch.databinding.ActivityBottomNavigationBinding
import com.dani.naversearch.enum.PageType


class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)

        initNavigationBar()
    }

    private fun initNavigationBar() {

        binding.bnvBottomNavi.setOnItemSelectedListener {
            val pageType = getPageType(it.itemId)
            changeFragment(pageType)
            true
        }
        changeFragment(PageType.BLOG)
    }

    private fun changeFragment(pageType: PageType) {
        val transaction = supportFragmentManager.beginTransaction()
        var targetFragment = supportFragmentManager.findFragmentByTag(pageType.tag)

        if (targetFragment == null) {
            targetFragment = getFragment(pageType)
            transaction.add(binding.fl.id, targetFragment, pageType.tag)
        }
        transaction.show(targetFragment)

        PageType.values()
            .filterNot { it == pageType }
            .forEach { type ->
                supportFragmentManager.findFragmentByTag(type.tag)?.let {
                    transaction.hide(it)
                }
            }
        transaction.commitAllowingStateLoss()
    }

    private fun getPageType(menuItemId: Int): PageType {
        return when (menuItemId) {
            R.id.action_blog_search -> PageType.BLOG
            R.id.action_cafe_search -> PageType.CAFE
            R.id.action_image_search -> PageType.IMAGE
            else -> throw IllegalArgumentException("not found menu item id")
        }
    }

    private fun getFragment(pageType: PageType): Fragment {
        return when (pageType.title) {
            "Image" -> {
                ImageSearchFragment.newInstance(pageType.title, pageType.key)
            }
            else -> {
                SearchFragment.newInstance(pageType.title, pageType.key)
            }
        }
    }
}
