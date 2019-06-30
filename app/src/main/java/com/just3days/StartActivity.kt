package com.just3days

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.just3days.BaseApp.Companion.prefHelper
import com.just3days.config.AppConstants
import com.just3days.data.AppPreferenceHeler
import kotlinx.android.synthetic.main.activity_main.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart(){
        super.onStart()
        if (prefHelper.firstRun) {
            setContentView(R.layout.activity_main)
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
        val intent: Intent = if (prefHelper.determinationContents == null)
            Intent(applicationContext, WriteTextActivity::class.java)
        else
            Intent(applicationContext, ShowTextActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        overridePendingTransition(0, 0)

        finish()
    }

}