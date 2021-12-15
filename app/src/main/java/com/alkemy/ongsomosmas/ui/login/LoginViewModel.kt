package com.alkemy.ongsomosmas.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alkemy.ongsomosmas.data.Preferences

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
}