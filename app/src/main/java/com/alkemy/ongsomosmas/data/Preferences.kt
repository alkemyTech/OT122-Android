package com.alkemy.ongsomosmas.data

import android.content.Context

class Preferences(val context: Context) {

    private val storage = context.getSharedPreferences("com.alkemy.ongsomosmas_app", Context.MODE_PRIVATE)

    fun saveUserToken(user: String) {
        storage.edit().putString(Companion.KEY_USER_TOKEN, user).apply()
    }

    fun getUserToken(): String {
        return storage.getString(Companion.KEY_USER_TOKEN, "")!!
    }

    fun clear() {
        storage.edit().clear().apply()
    }

    companion object {
        private const val KEY_USER_TOKEN = "user_token"
    }
}