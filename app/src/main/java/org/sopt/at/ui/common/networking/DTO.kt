package org.sopt.at.ui.common.networking

import kotlinx.serialization.Serializable

// 회원가입
@Serializable
data class RequestSignUpDto(
    val loginId: String,
    val password: String,
    val nickname: String
)

@Serializable
data class SignUpUserDataDto(
    val userId: Int,
    val nickname: String
)

@Serializable
data class ResponseSignUpDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpUserDataDto
)

// 로그인
@Serializable
data class RequestSignInDto(
    val loginId: String,
    val password: String
)

@Serializable
data class SignInUserDataDto(
    val userId: Int
)

@Serializable
data class ResponseSignInDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignInUserDataDto
)