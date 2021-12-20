package com.alkemy.ongsomosmas.data.signup

import com.alkemy.ongsomosmas.data.model.ApiResponse
import com.alkemy.ongsomosmas.data.model.User
import com.alkemy.ongsomosmas.data.model.signup.Data
import com.alkemy.ongsomosmas.data.model.signup.SignUpResponse
import retrofit2.Response
import javax.inject.Inject

class SignUpRepositoryImp @Inject constructor(val signUpDataSource: SignUpDataSource) : SignUpRepository {

    override suspend fun registerUser(user: User) = signUpDataSource.registerUser(user)


}