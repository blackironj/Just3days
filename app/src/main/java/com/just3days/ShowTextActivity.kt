package com.just3days

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.just3days.db.DeterminationDB
import kotlinx.android.synthetic.main.activity_show.*

class ShowTextActivity : AppCompatActivity() {

    private var determinationDB: DeterminationDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        determinationDB = DeterminationDB.getInstance(this)

        val loadDataTask = Runnable {
            val info = determinationDB?.determinationInfoDao()?.getData()

            if (info != null) {
                showText.text = info[0].content
            }
        }

        val loadThread = Thread(loadDataTask)

        loadThread.start()
    }
}
