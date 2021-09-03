package com.dani.naversearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.dani.naversearch.R
import com.dani.naversearch.databinding.ActivityMainBinding

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
                        replaceFragment(BlogSearchFragment())
                    }
                    R.id.action_cafe_search -> {
                        replaceFragment(CafeSearchFragment())
                    }
                    R.id.action_image_search -> {
                        replaceFragment(ImageSearchFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.action_blog_search
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fl.id, fragment).commit()
    }
}
