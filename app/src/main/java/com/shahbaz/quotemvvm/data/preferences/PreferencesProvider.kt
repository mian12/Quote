package com.shahbaz.quotemvvm.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val KEY_LAST_SAVED_AT = "key_saved_at"

class PreferencesProvider(
    context: Context
) {

    private val appContext = context.applicationContext


    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveLastSavedAt(lastSaveAt: String) {
        preference.edit().putString(KEY_LAST_SAVED_AT, lastSaveAt).apply()
    }

    fun getLastSavedAt(): String? {

        return preference.getString(KEY_LAST_SAVED_AT, null)
    }
}