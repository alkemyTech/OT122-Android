package com.alkemy.ongsomosmas.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.databinding.ActivitySignUpBinding
import com.alkemy.ongsomosmas.ui.BaseActivity
import com.alkemy.ongsomosmas.ui.login.LoginActivity
import com.alkemy.ongsomosmas.utils.afterTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
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
                showProgressDialog()
                signUpViewModel.signUp(
                    binding.etFirstName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }

        // Validate data
        checkValue()

        //Observer
        signUpViewModel.signUpFormState.observe(this, Observer {
            val signUpFormState = it ?: return@Observer

            with(binding) {
                // Button enabled
                cbTermsAndCondition.setOnClickListener {
                    btnRegister.isEnabled =
                        signUpFormState.isDataValid && cbTermsAndCondition.isChecked
                }
                btnRegister.isEnabled = signUpFormState.isDataValid && cbTermsAndCondition.isChecked
                etFirstName.error = signUpFormState.nameError?.let(::getString)
                etEmail.error = signUpFormState.emailError?.let(::getString)
                etPassword.error = signUpFormState.passwordError?.let(::getString)
                etConfirmPassword.error = signUpFormState.samePasswordError?.let(::getString)
            }
        })

        signUpViewModel.signUpResponse.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideProgressDialog()
                    MaterialAlertDialogBuilder(this).setTitle("Sign up")
                        .setMessage(R.string.User_was_succesfully_register)
                        .setPositiveButton("OK") { _, _ ->
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }.show()
                }

                Resource.Status.ERROR -> {
                    hideProgressDialog()
                }

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