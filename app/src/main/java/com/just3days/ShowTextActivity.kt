package com.just3days

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.just3days.db.DeterminationDB

class ShowTextActivity : AppCompatActivity() {

    private var determinationDB: DeterminationDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        determinationDB = DeterminationDB.getInstance(this)
    }
}
