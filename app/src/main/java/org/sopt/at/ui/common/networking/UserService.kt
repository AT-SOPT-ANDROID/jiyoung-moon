package org.sopt.at.ui.common.networking

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    // 내 닉네임 조회
    @GET("/api/v1/users/me")
    fun getMyNickname(
        @Header("userId") userId: Int
    ): Call<ResponseMyNicknameDto>
}