package org.sopt.at.ui.common.networking

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    val loginId: String,
    val password: String,
    val nickname: String
)

@Serializable
data class UserDataDto(
    val userId: Int,
    val nickname: String
)

@Serializable
data class ResponseSignUpDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: UserDataDto
)