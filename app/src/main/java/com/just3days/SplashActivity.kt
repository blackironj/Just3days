package com.just3days

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.just3days.config.SPLASH_DELAY
import com.just3days.db.DeterminationDB

class SplashActivity : AppCompatActivity() {

    private var determinationDB: DeterminationDB? = null

    private var delayHandler: Handler? = null

    private val runnable: Runnable = Runnable {
        determinationDB = DeterminationDB.getInstance(this)

        DoAsync {
            val item = determinationDB?.determinationInfoDao()?.isEmpty()

            if (!isFinishing) {
                val intent: Intent = if (item == 0)
                    Intent(applicationContext, WriteTextActivity::class.java)
                else
                    Intent(applicationContext, ShowTextActivity::class.java)

                startActivity(intent)
                finish()
            }
        }.execute()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        delayHandler = Handler()

        delayHandler!!.postDelayed(runnable, SPLASH_DELAY)
    }

    public override fun onDestroy() {
        if (delayHandler != null) {
            delayHandler!!.removeCallbacks(runnable)
        }

        super.onDestroy()
    }
}