package com.alkemy.ongsomosmas.ui.signup

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alkemy.ongsomosmas.R
import java.util.regex.Pattern

class SignUpViewModel @ViewModelInject constructor() : ViewModel() {

    private val _signUpFormState = MutableLiveData<SignUpFormState>()
    val signUpFormState: LiveData<SignUpFormState> = _signUpFormState

    private val _termsAndCondition = MutableLiveData<Boolean>()
    val termsAndConditions = _termsAndCondition

    // Validate Data
    fun onDataChanged(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (!isNameValid(name)) {
            _signUpFormState.value = SignUpFormState(nameError = R.string.name_text)
        } else if (!isEmailValid(email)) {
            _signUpFormState.value = SignUpFormState(emailError = R.string.email_text)
        } else if (!isPasswordValid(password)) {
            _signUpFormState.value =
                SignUpFormState(passwordError = R.string.password_text)
        } else if (!isPasswordSame(password, confirmPassword)) {
            _signUpFormState.value =
                SignUpFormState(samePasswordError = R.string.same_password_text)
        } else {
            _signUpFormState.value = SignUpFormState(isDataValid = true)
        }

    }


    // Check name
    private fun isNameValid(name: String) =
        Pattern.compile("^(?=\\S+\$).{3,}").matcher(name).matches()

    // Check email
    private fun isEmailValid(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    // Check password
    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+\$).{8,}"
        val pattern = Pattern.compile(passwordPattern)
        return pattern.matcher(password).matches()
    }

    private fun isPasswordSame(firstPass: String, secondPass: String) =
        firstPass == secondPass
}