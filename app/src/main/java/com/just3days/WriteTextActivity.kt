package com.just3days

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.just3days.BaseApp.Companion.prefHelper
import kotlinx.android.synthetic.main.activity_main.*

class WriteTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstRunInfo.visibility = View.GONE

        dtText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?)  = Unit
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)  = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s = p0.toString()
                if (s.length > 20) {
                    dtText.setText(s.subSequence(0, 20))
                    dtText.setSelection(20)
                }
            }
        })

        heartBtn.setOnClickListener {
            val currText = dtText.text.trim()
            if (currText.isNotEmpty()) {
                val currentTime = (System.currentTimeMillis() / 1000).toInt()

                prefHelper.startTime = currentTime
                prefHelper.lastCheckTime = currentTime
                prefHelper.determinationContents = dtText.text.toString()

                val intent = Intent(applicationContext, ShowTextActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)

                finish()
            }
        }
    }
}
