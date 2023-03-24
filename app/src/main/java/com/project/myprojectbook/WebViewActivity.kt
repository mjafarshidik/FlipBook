package com.project.myprojectbook

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.project.myprojectbook.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            webView.webViewClient = WebViewClient()

            // this will load the url of the website
            webView.loadUrl("https://dpmptsp.gresikkab.go.id/ebook-1/")

            // this will enable the javascript settings, it can also allow xss vulnerabilities
            webView.settings.javaScriptEnabled = true

            // if you want to enable zoom feature
            webView.settings.setSupportZoom(true)
        }
    }
}