package com.just3days

import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.just3days.config.NON_TRANSPARENCY
import com.just3days.config.THREE_DAYS
import com.just3days.db.DeterminationDB
import com.just3days.db.DeterminationInfo
import kotlinx.android.synthetic.main.activity_show.*

class ShowTextActivity : AppCompatActivity() {
    private val determinationDB: DeterminationDB? = DeterminationDB.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        GetDataAsyncTask(showText, btnRenewData, determinationDB!!).execute()
    }

    class GetDataAsyncTask(textView: TextView, renewbtn: Button, db: DeterminationDB) :
        AsyncTask<Unit, Unit, DeterminationInfo?>() {
        private val db: DeterminationDB? = db

        val innerRenewbtn: Button? = renewbtn
        val innerTextView: TextView? = textView

        override fun doInBackground(vararg params: Unit?): DeterminationInfo? {
            val info = db?.determinationInfoDao()?.getData()
            return info!![0]
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onPostExecute(result: DeterminationInfo?) {
            super.onPostExecute(result)

            val alpha = result?.last_check_time?.let { calculateAlpha(it) }
            if (alpha != null) {
                innerTextView?.setTextColor(Color.argb(alpha, 0, 0, 0))
                innerTextView?.text = result.content
            } else {
                db?.determinationInfoDao()?.deleteData()
            }

            innerRenewbtn?.setOnClickListener {
                val currentTime = (System.currentTimeMillis() / 1000).toInt()
                val runRenewDataTask = Runnable {
                    if (result != null) {
                        result.id?.let { id -> db?.determinationInfoDao()?.update(id, currentTime) }
                    }
                    innerTextView?.setTextColor(Color.argb(NON_TRANSPARENCY, 0, 0, 0))
                }
                val renewThread = Thread(runRenewDataTask)
                renewThread.start()
            }
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
}
