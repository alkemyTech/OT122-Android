package com.alkemy.ongsomosmas.data.signup

import com.alkemy.ongsomosmas.data.Resource
import com.alkemy.ongsomosmas.data.model.ApiResponse
import com.alkemy.ongsomosmas.data.model.User
import com.alkemy.ongsomosmas.data.model.signup.Data
import com.alkemy.ongsomosmas.data.model.signup.SignUpResponse
import retrofit2.Response

interface SignUpRepository {
    suspend fun registerUser(user: User) : Response<SignUpResponse>
}