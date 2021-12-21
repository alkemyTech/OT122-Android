package com.alkemy.ongsomosmas.ui.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.signup.SignUpResponse
import com.alkemy.ongsomosmas.data.signup.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel @ViewModelInject constructor(private val signUpRepository: SignUpRepository) :
    ViewModel() {

    private val _signUpResponse: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()
    val signUpResponse: LiveData<Resource<SignUpResponse>> = _signUpResponse


        fun signUp(name: String, email: String, password: String) = viewModelScope.launch(Dispatchers.Main){
            val result = withContext(Dispatchers.IO){
                signUpRepository.registerUser(name, email, password)
            }

            _signUpResponse.value = result
        }



}
