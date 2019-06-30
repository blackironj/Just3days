package com.just3days

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.just3days.BaseApp.Companion.prefHelper
import com.just3days.config.NON_TRANSPARENCY
import com.just3days.config.THREE_DAYS
import kotlinx.android.synthetic.main.activity_main.*

class ShowTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstRunInfo.visibility = View.GONE
        dtText.isEnabled = false

        val currentTime = (System.currentTimeMillis() / 1000).toInt()
        val alpha = calculateAlpha(currentTime, prefHelper.lastCheckTime)

        if (alpha == null) {
            //TODO : it should be handled depending on success

            prefHelper.startTime = 0
            prefHelper.lastCheckTime = 0
            prefHelper.determinationContents = null

        } else {
            setTextTransparency(alpha)

            if (prefHelper.determinationContents != null)
                dtText.setText(prefHelper.determinationContents)
        }

        heartText.setOnClickListener {
            prefHelper.lastCheckTime = (System.currentTimeMillis() / 1000).toInt()
            setTextTransparency(NON_TRANSPARENCY)
        }
    }

    private fun setTextTransparency(alpha: Int){
        heartText.setTextColor(Color.argb(alpha, 0, 0, 0))
        dtText.setTextColor(Color.argb(alpha, 0, 0, 0))
    }

    private fun calculateAlpha(currentTime: Int, lastCheckTime: Int): Int? {
        val difference = currentTime - lastCheckTime
        if (difference >= THREE_DAYS)
            return null

        val ratio = difference.toFloat() / THREE_DAYS.toFloat()

        return NON_TRANSPARENCY - (NON_TRANSPARENCY * ratio).toInt()
    }
}
