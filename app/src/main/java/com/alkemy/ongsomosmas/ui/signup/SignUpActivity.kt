package com.alkemy.ongsomosmas.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.databinding.ActivitySignUpBinding
import com.alkemy.ongsomosmas.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.isEnabled = false

        binding.btnRegister.setOnClickListener {
            signUpViewModel.signUp(
                binding.etFirstName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }


        signUpViewModel.signUpResponse.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    MaterialAlertDialogBuilder(this).setTitle("Sign up").setMessage(R.string.User_was_succesfully_register)
                        .setPositiveButton("OK") { _, _ ->
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }.show()
                }

                Resource.Status.ERROR -> {

                }

            }
        })

    }
}