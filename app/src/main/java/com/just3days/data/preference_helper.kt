package com.just3days.data

import android.content.Context

interface PreferenceHelper {
    var determinationContents: String?
    var startTime: Int
    var lastCheckTime: Int
    var firstRun: Boolean
}

class AppPreferenceHeler constructor(
    val context: Context,
    prefFileName: String
) : PreferenceHelper {
    private val PREF_KEY_CONTENTS: String = "PREF_KEY_CONTENTS"
    private val PREF_KEY_START_TIME: String = "PREF_KEY_START_TIME"
    private val PREF_KEY_RENEW_TIME: String = "PREF_KEY_RENEW_TIME"
    private val PREF_KEY_FIRST_RUN: String = "PREF_KEY_FIRST_RUN"

    private val prefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override var determinationContents: String?
        get() = prefs.getString(PREF_KEY_CONTENTS, null)
        set(value) = prefs.edit().putString(PREF_KEY_CONTENTS, value).apply()

    override var startTime: Int
        get() = prefs.getInt(PREF_KEY_START_TIME, 0)
        set(value) = prefs.edit().putInt(PREF_KEY_START_TIME, value).apply()

    override var lastCheckTime: Int
        get() = prefs.getInt(PREF_KEY_START_TIME, 0)
        set(value) = prefs.edit().putInt(PREF_KEY_RENEW_TIME, value).apply()

    override var firstRun: Boolean
        get() = prefs.getBoolean(PREF_KEY_FIRST_RUN, true)
        set(value) = prefs.edit().putBoolean(PREF_KEY_FIRST_RUN, value).apply()
}