package com.project.myprojectbook

import android.content.Context
import com.shevelev.page_turning_lib.page_curling.textures_manager.repository.BitmapProvider
import java.io.InputStream

class RawResourcesBitmapProvider (private val context: Context) : BitmapProvider {
    private val bitmapIds = listOf(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6)

    /**
     * Total quantity of bitmap
     */
    override val total: Int
        get() = bitmapIds.size

    /**
     * Returns a stream of bitmap data for given bitmap index
     * The stream will be closed by caller
     */
    override fun getBitmapStream(index: Int): InputStream = context.resources.openRawResource(bitmapIds[index])
}