package com.just3days

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.just3days.BaseApp.Companion.prefHelper
import com.just3days.config.AppConstants
import com.just3days.config.THREE_DAYS
import kotlinx.android.synthetic.main.activity_main.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (prefHelper.firstRun) {
            prefHelper.firstRun = false

            firstRunInfo.text = AppConstants.INFO

            firstRunInfo.setOnClickListener {
                moveToNextActivity()
            }
        } else {
            moveToNextActivity()
        }
    }

    private fun moveToNextActivity() {
        val intent: Intent
        intent = if (prefHelper.determinationContents == null) {
            Intent(applicationContext, WriteTextActivity::class.java)
        } else {
            val currTime = (System.currentTimeMillis() / 1000).toInt()
            val lastCheckTime = prefHelper.lastCheckTime
            if (currTime - lastCheckTime >= THREE_DAYS) {
                prefHelper.determinationContents = null
                prefHelper.lastCheckTime = 0
                prefHelper.startTime = 0
                Intent(applicationContext, WriteTextActivity::class.java)
            } else
                Intent(applicationContext, ShowTextActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        overridePendingTransition(0, 0)

        finish()
    }

}