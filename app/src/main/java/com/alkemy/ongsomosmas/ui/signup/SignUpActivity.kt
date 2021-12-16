package com.alkemy.ongsomosmas.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alkemy.ongsomosmas.databinding.ActivitySignUpBinding
import com.alkemy.ongsomosmas.utils.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
    }

    private fun setup() {
        //Button enabled
        binding.btnRegister.isEnabled = false

        //Listeners
        with(binding) {
            btnRegister.setOnClickListener {
                Toast.makeText(this@SignUpActivity, "Calling the API", Toast.LENGTH_SHORT).show()
            }

            cbTermsAndCondition.setOnClickListener {

            }
        }


        // Validate data
        checkValue()

        //Observer
        signUpViewModel.signUpFormState.observe(this, Observer {
            val signUpFormState = it ?: return@Observer

            with(binding) {
                // Button enabled
                cbTermsAndCondition.isEnabled = signUpFormState.isDataValid
                cbTermsAndCondition.setOnClickListener{
                    btnRegister.isEnabled =
                        signUpFormState.isDataValid && cbTermsAndCondition.isChecked
                }
                btnRegister.isEnabled = signUpFormState.isDataValid && cbTermsAndCondition.isChecked
                etFirstName.error = signUpFormState.nameError?.let(::getString)
                etEmail.error = signUpFormState.emailError?.let(::getString)
                etPassword.error = signUpFormState.passwordError?.let(::getString)
                etConfirmPassword.error = signUpFormState.samePasswordError?.let(::getString)
//                btnRegister.isEnabled = signUpFormState.isChecked
            }
        })
    }

    private fun checkValue() {
        with(binding) {
            etFirstName.afterTextChanged {
                signUpViewModel.onDataChanged(
                    etFirstName.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }

            etEmail.afterTextChanged {
                signUpViewModel.onDataChanged(
                    etFirstName.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }

            etPassword.afterTextChanged {
                signUpViewModel.onDataChanged(
                    etFirstName.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }

            etConfirmPassword.afterTextChanged {
                signUpViewModel.onDataChanged(
                    etFirstName.text.toString(),
                    etEmail.text.toString(),
                    etPassword.text.toString(),
                    etConfirmPassword.text.toString()
                )
            }


        }
    }

}