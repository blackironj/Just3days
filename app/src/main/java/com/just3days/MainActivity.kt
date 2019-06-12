package com.just3days

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.just3days.db.DeterminationDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var determinationDB : DeterminationDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        determinationDB = DeterminationDB.getInstance(this)

        //val numOfData = determinationDB?.determinationInfoDao()?.isEmpty()


        var prevText = ""
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                prevText = s.toString()
            }

            override fun afterTextChanged(editable: Editable?) {
                if (editText.lineCount >= 10) {
                    editText.setText(prevText)
                    editText.setSelection(editText.length())
                }
            }
        })
    }
}
