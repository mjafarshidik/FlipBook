package com.project.myprojectbook

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.shevelev.page_turning_lib.page_curling.CurlView
import com.shevelev.page_turning_lib.page_curling.CurlViewEventsHandler
import com.shevelev.page_turning_lib.page_curling.textures_manager.PageLoadingEventsHandler
import com.shevelev.page_turning_lib.user_actions_managing.Area
import com.shevelev.page_turning_lib.user_actions_managing.Point

class FlipBookActivity : AppCompatActivity() {
    private var curlView: CurlView? = null
    private var currentPageIndex: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_book)
        currentPageIndex =
            savedInstanceState?.getInt(CURRENT_PAGE) ?: intent.getIntExtra(START_PAGE, 0)

        curlView = (findViewById<View>(R.id.curl) as? CurlView)?.also {
            it.setBitmapProvider(RawResourcesBitmapProvider(this))
            it.initCurrentPageIndex(currentPageIndex!!)
            it.setBackgroundColor(Color.WHITE)
            it.setHotAreas(listOf(Area(0, Point(0, 0), Size(100, 100))))

            it.setExternalEventsHandler(object : CurlViewEventsHandler {
                override fun onPageChanged(newPageIndex: Int) {
                    currentPageIndex = newPageIndex
                }

                override fun onHotAreaPressed(areaId: Int) {
                    it.setCurrentPageIndex(2)
                    Toast.makeText(
                        this@FlipBookActivity,
                        "We've moved to the page with index 2",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            it.setOnPageLoadingListener(object : PageLoadingEventsHandler {
                override fun onLoadingStarted() {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }

                override fun onLoadingCompleted() {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }

                override fun onLoadingError() {
                    Toast.makeText(this@FlipBookActivity, "Oh… Something happened", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
    }

    public override fun onPause() {
        super.onPause()
        curlView!!.onPause()
    }

    public override fun onResume() {
        super.onResume()
        curlView!!.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_PAGE, currentPageIndex ?: 0)
    }

    companion object {
        private const val START_PAGE = "START_PAGE"
        private const val CURRENT_PAGE = "CURRENT_PAGE"

        fun start(parentActivity: Activity, startPage: Int) {
            val intent =
                Intent(parentActivity, MainActivity::class.java).putExtra(START_PAGE, startPage)
            parentActivity.startActivity(intent)
        }
    }
}