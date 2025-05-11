package org.sopt.at.ui.onboarding.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.common.navigation.NavRoutes
import org.sopt.at.ui.common.networking.RequestSignUpDto
import org.sopt.at.ui.common.networking.ResponseSignUpDto
import org.sopt.at.ui.common.networking.ServicePool
import org.sopt.at.ui.onboarding.component.InputFieldComponent
import org.sopt.at.ui.theme.TvingTheme.colors
import retrofit2.Callback
import java.util.regex.Pattern

@Composable
fun SignUpScreen(navController: NavController) {
    var currentStep by remember { mutableStateOf(0) }   // 0: ID, 1: PWD, 2: Nickname
    var isPwdVisible by remember { mutableStateOf(false) }

    var idInputText by remember { mutableStateOf("") }
    var pwdInputText by remember { mutableStateOf("") }
    var nicknameInputText by remember { mutableStateOf("") }

    val context = LocalContext.current

    // id 유효성 검사하는 함수
    fun isValidId(id: String): Boolean {
        val regex = "^[A-Za-z0-9]{8,20}$"
        return Pattern.matches(regex, id)
    }

    // pwd 유효성 검사하는 함수
    fun isValidPwd(pwd: String): Boolean {
        val regex = "^[A-Za-z0-9]{8,20}$"
        return Pattern.matches(regex, pwd)
    }

    // 닉네임 유효성 검사하는 함수
    fun isValidNickname(nickname: String): Boolean {
        val regex = "^[가-힣a-zA-Z0-9]{1,20}$"
        return Pattern.matches(regex, nickname)
    }

    when (currentStep) {
        // '아이디를 입력해주세요.' 화면
        0 -> SignUpSingleScreen(
            onLeftIconClicked = { navController.popBackStack() },
            enterGuideId = R.string.id_enter_guide,
            inputfieldPlaceholderId = R.string.id_kor,
            inputText = idInputText,
            onTextChanged = { idInputText = it },
            inputfieldRuleId = R.string.id_rule,
            onNextBtnClicked = {
                if (isValidId(idInputText)) {
                    currentStep = 1
                } else {
                    if (idInputText.isNotBlank()) {
                        Toast.makeText(context, "아이디 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "아이디를 입력해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        )

        // '비밀번호를 입력해주세요.' 화면
        1 -> SignUpSingleScreen(
            onLeftIconClicked = {
                currentStep = 0
            },
            enterGuideId = R.string.pwd_enter_guide,
            inputfieldPlaceholderId = R.string.pwd_kor,
            inputText = pwdInputText,
            onTextChanged = { pwdInputText = it },
            inputfieldRuleId = R.string.pwd_rule,
            isPwdField = true,
            isPwdVisible = isPwdVisible,
            onPwdVisibleToggle = { isPwdVisible = !isPwdVisible },
            onNextBtnClicked = {
                if (isValidPwd(pwdInputText)) {
                    currentStep = 2
                } else {
                    if (pwdInputText.isNotBlank()) {
                        Toast.makeText(context, "비밀번호 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        )

        // '닉네임을 입력해주세요.' 화면
        2 -> SignUpSingleScreen(
            onLeftIconClicked = {
                currentStep = 1
            },
            enterGuideId = R.string.nickname_enter_guide,
            inputfieldPlaceholderId = R.string.nickname_kor,
            inputText = nicknameInputText,
            onTextChanged = { nicknameInputText = it },
            inputfieldRuleId = R.string.nickname_rule,
            onNextBtnClicked = {
                if (isValidNickname(nicknameInputText)) {
                    val request = RequestSignUpDto(
                        loginId = idInputText,
                        password = pwdInputText,
                        nickname = nicknameInputText
                    )

                    ServicePool.userService.postSignUp(request)
                        .enqueue(object : Callback<ResponseSignUpDto> {
                            override fun onResponse(
                                call: retrofit2.Call<ResponseSignUpDto>,
                                response: retrofit2.Response<ResponseSignUpDto>
                            ) {
                                if (response.isSuccessful && response.body()?.success == true) {
                                    Toast.makeText(
                                        context,
                                        "회원가입이 완료되었습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navController.navigate(NavRoutes.SignIn.route)
                                } else {
                                    val message =
                                        response.body()?.message ?: "회원가입에 실패했습니다."
                                    Toast.makeText(
                                        context,
                                        message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(
                                call: retrofit2.Call<ResponseSignUpDto>,
                                t: Throwable
                            ) {
                                Toast.makeText(
                                    context,
                                    "네트워크 오류: ${t.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })

                } else {
                    if (nicknameInputText.isNotBlank()) {
                        Toast.makeText(context, "닉네임 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            },
        )
    }
}

@Composable
fun SignUpSingleScreen(
    onLeftIconClicked: () -> Unit = {},  // 상단바 왼쪽 아이콘 클릭 시 콜백 함수
    enterGuideId: Int,  // '~를 입력해주세요.' text
    inputfieldPlaceholderId: Int,  // placeholder => '아이디' or '비밀번호' text
    inputText: String,
    onTextChanged: (String) -> Unit,
    inputfieldRuleId: Int,  // 입력 규칙
    onNextBtnClicked: () -> Unit,
    // 비밀번호 필드 관련
    isPwdField: Boolean = false,  // 해당 inputfield가 pwd를 다루는 필드인지 여부
    isPwdVisible: Boolean = true,  // 현재 text가 보이는 상태인지에 대한 여부
    onPwdVisibleToggle: (() -> Unit)? = null,  // 눈 모양 아이콘 클릭 시 호출할 콜백 함수
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.BasicBlack)
            .padding(20.dp)
    ) {
        // 상단바
        TopAppBarComponent(
            leftIconId = R.drawable.ic_round_arrow_back_ios_24,
            onLeftIconClicked = onLeftIconClicked
        )

        Spacer(modifier = Modifier.height(20.dp))

        // '~를 입력해주세요.' text
        Text(
            text = stringResource(enterGuideId),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colors.BasicWhite,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // inputfield
        InputFieldComponent(
            placeholder = stringResource(inputfieldPlaceholderId),
            inputText = inputText,
            onTextChanged = onTextChanged,
            isPwdField = isPwdField,
            isPwdVisible = isPwdVisible,
            onPwdVisibleToggle = onPwdVisibleToggle
        )

        Spacer(modifier = Modifier.height(20.dp))

        // inputfield 규칙
        Text(
            text = stringResource(inputfieldRuleId),
            fontSize = 14.sp,
            color = colors.Gray3
        )

        Spacer(modifier = Modifier.weight(1f))

        // '다음' btn
        ButtonComponent(
            containerColor = colors.BasicBlack,
            contentColor = colors.Gray3,
            text = stringResource(R.string.next),
            onClick = onNextBtnClicked,
            strokeColor = colors.Gray3
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}