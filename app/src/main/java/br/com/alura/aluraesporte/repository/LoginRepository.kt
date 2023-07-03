package br.com.alura.aluraesporte.repository

import android.content.SharedPreferences
import androidx.core.content.edit

class LoginRepository(
    private val preferences: SharedPreferences
) {
    fun setLoggedIn() {
        preferences.edit {
            putBoolean(IS_LOGGED_KEY, true)
        }
    }

    fun setLoggedOut() {
        preferences.edit {
            putBoolean(IS_LOGGED_KEY, false)
        }
    }

    fun isLogged(): Boolean = preferences.getBoolean(IS_LOGGED_KEY, false)

    companion object {
        private const val IS_LOGGED_KEY: String = "IS_LOGGED_KEY"
    }
}
