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


    /*fun registerUser(user: User) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(ResultAux.Loading())
        try {
            emit(ResultAux.Success(signUpRepository.registerUser(user)))
        } catch (e: Exception) {
            emit(ResultAux.Failure(e))
        }
    }*/

    private val _signUpResponse: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()
    val signUpResponse: LiveData<Resource<SignUpResponse>> = _signUpResponse


        fun signUp(name: String, email: String, password: String) = viewModelScope.launch(Dispatchers.Main){
            val result = withContext(Dispatchers.IO){
                signUpRepository.registerUser(name, email, password)
            }

            _signUpResponse.value = result
        }



}
