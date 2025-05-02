package org.sopt.at.ui.onboarding.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.onboarding.component.InputFieldComponent
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.Gray3
import java.util.regex.Pattern

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                var isFirstStep by remember { mutableStateOf(true) }
                var isPwdVisible by remember { mutableStateOf(false) }

                var idInputText by remember { mutableStateOf("") }
                var pwdInputText by remember { mutableStateOf("") }

                // id 유효성 검사하는 함수
                fun isValidId(input: String): Boolean {
                    val regex = "^[a-z0-9]{6,12}$".toRegex()
                    return input.matches(regex)
                }

                // pwd 유효성 검사하는 함수
                fun isValidPwd(input: String): Boolean {
                    val regex =
                        Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#\$%^&*])[A-Za-z\\d~!@#\$%^&*]{8,15}")
                    return regex.matcher(input).matches()
                }

                if (isFirstStep) {
                    // '아이디를 입력해주세요.' 화면
                    SignUpScreen(
                        onLeftIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ },
                        enterGuideId = R.string.id_enter_guide,
                        inputfieldPlaceholderId = R.string.id_kor,
                        inputText = idInputText,
                        onTextChanged = { idInputText = it },
                        inputfieldRuleId = R.string.id_rule,
                        onNextBtnClicked = {
                            if (isValidId(idInputText)) {
                                isFirstStep = false
                            } else {
                                Toast.makeText(this, "아이디 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
                if (!isFirstStep) {
                    // '비밀번호를 입력해주세요.' 화면
                    SignUpScreen(
                        onLeftIconClicked = {
                            isFirstStep = true
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
                                Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT)
                                    .show()
                                val intent =
                                    Intent(this@SignUpActivity, SignInActivity::class.java).apply {
                                        putExtra("id", idInputText)
                                        putExtra("pwd", pwdInputText)
                                    }
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "비밀번호 형식을 다시 확인해주세요.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
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
            .background(Color.Black)
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
            color = Color.White,
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
            color = Gray3
        )

        Spacer(modifier = Modifier.weight(1f))

        // '다음' btn
        ButtonComponent(
            containerColor = Color.Black,
            contentColor = Gray3,
            text = stringResource(R.string.next),
            onClick = onNextBtnClicked,
            strokeColor = Gray3
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}