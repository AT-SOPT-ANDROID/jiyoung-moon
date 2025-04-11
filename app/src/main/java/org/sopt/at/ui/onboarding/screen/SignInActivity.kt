package org.sopt.at.ui.onboarding.screen

import android.os.Bundle
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.onboarding.component.InputFieldComponent
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.Gray100
import org.sopt.at.ui.theme.Gray200

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignInScreen()
            }
        }
    }
}

@Preview
@Composable
fun SignInScreen() {
    var idInputText by remember { mutableStateOf("") }
    var pwdInputText by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }

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
            containerColor = Gray200,
            contentColor = Gray100,
            text = stringResource(R.string.login_action),
            onClick = { /* TODO: 버튼 클릭 시 처리 */ }
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
                color = Gray100,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            Text(
                text = "  |  ",
                fontSize = 16.sp,
                color = Gray100
            )
            Text(  // 비밀번호 찾기
                text = stringResource(R.string.pwd_kor) + " " + stringResource(R.string.find),
                fontSize = 16.sp,
                color = Gray100,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            Text(
                text = "  |  ",
                fontSize = 16.sp,
                color = Gray100
            )
            Text(  // 회원가입
                text = stringResource(R.string.signup),
                fontSize = 16.sp,
                color = Gray100,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
        }
    }
}