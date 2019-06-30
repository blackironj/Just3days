package com.just3days

import android.app.Application
import com.just3days.config.AppConstants
import com.just3days.data.AppPreferenceHeler
import com.just3days.data.PreferenceHelper

class BaseApp : Application() {

    companion object {
        lateinit var prefHelper: PreferenceHelper
            private set
    }

    override fun onCreate() {
        prefHelper = AppPreferenceHeler(applicationContext, AppConstants.PREF_NAME)

        super.onCreate()
    }
}