package com.just3days

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.just3days.config.NON_TRANSPARENCY
import com.just3days.config.THREE_DAYS
import com.just3days.db.DeterminationDB
import kotlinx.android.synthetic.main.activity_show.*

class ShowTextActivity : AppCompatActivity() {

    private var determinationDB: DeterminationDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        determinationDB = DeterminationDB.getInstance(this)

        val loadDataTask = Runnable {
            val info = determinationDB?.determinationInfoDao()?.getData() ?: return@Runnable

            val alpha = calculateAlpha(info[0].last_check_time)

            if (alpha != null) {
                showText.setTextColor(Color.argb(alpha, 0, 0, 0))
                showText.text = info[0].content
            } else {
                determinationDB?.determinationInfoDao()?.deleteData()
            }
        }
        val loadThread = Thread(loadDataTask)
        loadThread.start()
    }

    private fun calculateAlpha(lastCheckTime: Int): Int? {
        val currentTime = (System.currentTimeMillis() / 1000).toInt()

        val difference = currentTime - lastCheckTime
        if (difference >= THREE_DAYS)
            return null

        val ratio = difference.toFloat() / THREE_DAYS.toFloat()

        return NON_TRANSPARENCY - (NON_TRANSPARENCY * ratio).toInt()
    }
}
