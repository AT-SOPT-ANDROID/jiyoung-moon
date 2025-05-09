package org.sopt.at.ui.common.networking

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/v1/auth/signup")
    fun postSignUp(
        @Body user: RequestSignUpDto
    ): Call<ResponseSignUpDto>
}