package com.dani.naversearch.ui

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dani.naversearch.R
import com.dani.naversearch.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)

        val url: String = intent.getStringExtra("url").toString()
        initWebView(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(address: String) {

        val webView = binding.webView

        // 와이파이 & 데이터 연결되어 있으면 웹뷰 생성
        if (getNetworkConnected(this)) {

            webView.settings.javaScriptEnabled = true // 자바 스크립트 허용

            // 웹뷰안에 새 창이 뜨지 않도록 방지
            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()

            webView.loadUrl(address)

        } else {
            finish()
        }
    }

    // 인터넷 연결 확인
    private fun getNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        return activeNetwork?.isConnectedOrConnecting == true
    }
}