package org.sopt.at.ui.onboarding.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.mypage.screen.MyActivity
import org.sopt.at.ui.onboarding.component.InputFieldComponent
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.Gray3
import org.sopt.at.ui.theme.Gray4

class SignInActivity : ComponentActivity() {
    private var registeredId: String? = null
    private var registeredPwd: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 회원가입에서 넘어온 정보 받기
        registeredId = intent.getStringExtra("id")
        registeredPwd = intent.getStringExtra("pwd")

        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignInScreen(registeredId, registeredPwd)
            }
        }
    }
}

@Composable
fun SignInScreen(
    registeredId: String?,
    registeredPwd: String?
) {
    var idInputText by remember { mutableStateOf("") }
    var pwdInputText by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        // 상단바
        TopAppBarComponent(
            leftIconId = R.drawable.ic_round_arrow_back_ios_24,
            onLeftIconClicked = { /* TODO: 뒤로 가기 처리 */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 'TVING ID 로그인' text
        Text(
            text = stringResource(R.string.tving_eng) + " "
                    + stringResource(R.string.id_eng) + " "
                    + stringResource(R.string.login),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 아이디 inputfield
        InputFieldComponent(
            placeholder = stringResource(R.string.id_kor),
            inputText = idInputText,
            onTextChanged = { idInputText = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 비밀번호 inputfield
        InputFieldComponent(
            placeholder = stringResource(R.string.pwd_kor),
            inputText = pwdInputText,
            onTextChanged = { pwdInputText = it },
            isPwdField = true,
            isPwdVisible = isPwdVisible,
            onPwdVisibleToggle = { isPwdVisible = !isPwdVisible }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // '로그인하기' btn
        ButtonComponent(
            containerColor = Gray4,
            contentColor = Gray3,
            text = stringResource(R.string.login_action),
            onClick = {
                if (idInputText == registeredId && pwdInputText == registeredPwd) {
                    // 로그인 성공 → MyActivity로 이동
                    val intent = Intent(context, MyActivity::class.java).apply {
                        putExtra("id", idInputText)
                        putExtra("pwd", pwdInputText)
                    }
                    context.startActivity(intent)
                } else {
                    // 로그인 실패 → Toast 띄우기
                    Toast.makeText(context, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(  // 아이디 찾기
                text = stringResource(R.string.id_kor) + " " + stringResource(R.string.find),
                fontSize = 16.sp,
                color = Gray3,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            Text(
                text = "  |  ",
                fontSize = 16.sp,
                color = Gray3
            )
            Text(  // 비밀번호 찾기
                text = stringResource(R.string.pwd_kor) + " " + stringResource(R.string.find),
                fontSize = 16.sp,
                color = Gray3,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            Text(
                text = "  |  ",
                fontSize = 16.sp,
                color = Gray3
            )
            Text(  // 회원가입
                text = stringResource(R.string.signup),
                fontSize = 16.sp,
                color = Gray3,
                modifier = Modifier.clickable {
                    val intent = Intent(context, SignUpActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}