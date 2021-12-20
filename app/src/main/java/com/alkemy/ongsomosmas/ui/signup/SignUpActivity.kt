package com.alkemy.ongsomosmas.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alkemy.ongsomosmas.data.model.User
import com.alkemy.ongsomosmas.databinding.ActivitySignUpBinding
import com.alkemy.ongsomosmas.utils.ResultAux
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener {

            //User only for testing purpuse
            val user: User = User("Test0015", "test0015@gmail.com", "qwerty123")
            //TODO Validate entries

            signUpViewModel.registerUser(user).observe(this, Observer { result ->
                when (result) {
                    is ResultAux.Loading -> {
                        Log.d("SIGNUP-STATUS", "Loading...")
                    }
                    is ResultAux.Success -> {
                        Log.d("SIGNUP-STATUS", "Succesful: ${result.data.body()!!.message}")
                    }
                    is ResultAux.Failure -> {
                        Log.d("SIGNUP-STATUS", "ERROR: ${result.exception}")
                    }
                }
            })
        }


    }
}