package org.sopt.at.ui.common.networking

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    // 회원가입
    @POST("/api/v1/auth/signup")
    fun postSignUp(
        @Body user: RequestSignUpDto
    ): Call<ResponseSignUpDto>

    // 로그인
    @POST("/api/v1/auth/signin")
    fun postSignIn(
        @Body user: RequestSignInDto
    ): Call<ResponseSignInDto>
}