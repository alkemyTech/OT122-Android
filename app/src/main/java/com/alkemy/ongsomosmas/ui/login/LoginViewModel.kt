package com.alkemy.ongsomosmas.ui.login

import android.util.Log
import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alkemy.ongsomosmas.data.Preferences
import java.util.regex.Pattern

class LoginViewModel @ViewModelInject constructor(
    private val preference: Preferences
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    fun loginDataChanged(email: String, password: String) {
        // TODO
    }

    fun isUserLogged(): Boolean {
        return preference.getUserToken().isNotEmpty()
    }

    fun validEmailPassword(email: String?, pwd: String?): Boolean {
        var btnState = true
        var reg = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-zA-Z])" +
                ".{8,}\$"
        try {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) btnState = false
            if(!Pattern.matches(reg, pwd)) btnState = false
        }catch (e: Exception) {
            Log.d("EmailPasswordValidation", e.message.toString())
        }
        return btnState
    }
}