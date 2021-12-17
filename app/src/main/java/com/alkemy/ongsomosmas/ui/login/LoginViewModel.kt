package com.alkemy.ongsomosmas.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alkemy.ongsomosmas.data.Preferences
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.contactus.ContactUsRepository
import com.alkemy.ongsomosmas.data.login.LoginRepository
import com.alkemy.ongsomosmas.data.model.ContactResponse
import com.alkemy.ongsomosmas.data.model.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel @ViewModelInject constructor(
    private val preference: Preferences,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>> = _loginResponse

    fun loginDataChanged(email: String, password: String) {
        // TODO
    }

    fun persistUserToken(token: String) {
        return preference.saveUserToken(token)
    }

    fun isUserLogged(): Boolean {
        return preference.getUserToken().isNotEmpty()
    }

    fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.Main) {
        val result = withContext(Dispatchers.IO) {
            loginRepository.login(
                email,
                password
            )
        }
        _loginResponse.value = result

    }

}