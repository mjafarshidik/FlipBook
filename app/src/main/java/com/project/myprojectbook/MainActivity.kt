package com.project.myprojectbook

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.myprojectbook.databinding.ActivityMainBinding
import com.shevelev.page_turning_lib.page_curling.CurlView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intentToNextActivity()
    }

    private fun intentToNextActivity() {
        binding.apply {
            btnFlipBook.setOnClickListener {
                val intent = Intent(this@MainActivity, FlipBookActivity::class.java)
                startActivity(intent)
            }

            btnWebView.setOnClickListener {
                val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
