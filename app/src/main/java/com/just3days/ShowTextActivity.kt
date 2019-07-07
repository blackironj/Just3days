package com.just3days

import android.content.Intent
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
            prefHelper.startTime = 0
            prefHelper.lastCheckTime = 0
            prefHelper.determinationContents = null

            val intent = Intent(applicationContext, WriteTextActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NO_ANIMATION
            startActivity(intent)
            overridePendingTransition(0, 0)

            finish()
        } else {
            setTextTransparency(alpha)

            if (prefHelper.determinationContents != null)
                dtText.setText(prefHelper.determinationContents)
        }

        heartBtn.setOnClickListener {
            prefHelper.lastCheckTime = (System.currentTimeMillis() / 1000).toInt()
            setTextTransparency(NON_TRANSPARENCY)
        }
    }

    private fun setTextTransparency(alpha: Int) {
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
