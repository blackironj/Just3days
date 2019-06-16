package com.just3days

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.just3days.db.DeterminationDB
import com.just3days.db.DeterminationInfo
import kotlinx.android.synthetic.main.activity_write.*

class WriteTextActivity : AppCompatActivity() {

    private var determinationDB: DeterminationDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        determinationDB = DeterminationDB.getInstance(this)

        var prevText = ""
        writeText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                prevText = s.toString()
            }

            override fun afterTextChanged(editable: Editable?) {
                if (writeText.lineCount >= 10) {
                    writeText.setText(prevText)
                    writeText.setSelection(writeText.length())
                }
            }
        })

        btnSaveData.setOnClickListener {
            val currentTime = (System.currentTimeMillis() / 1000).toInt()

            val newInfo = DeterminationInfo(
                content = writeText.text.toString(),
                start_time = currentTime,
                last_check_time = currentTime
            )

            val runSaveDataTask = Runnable {
                determinationDB?.determinationInfoDao()?.insert(newInfo)

                finish()

                System.exit(0)
            }
            val writeThread = Thread(runSaveDataTask)
            writeThread.start()
        }

    }
}
