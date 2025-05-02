package org.sopt.at.ui.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.theme.Gray3
import org.sopt.at.ui.theme.Gray5

@Composable
fun InputFieldComponent(
    placeholder: String,
    inputText: String,
    onTextChanged: (String) -> Unit,
    // 비밀번호 필드 관련
    isPwdField: Boolean = false,  // 해당 inputfield가 pwd를 다루는 필드인지 여부
    isPwdVisible: Boolean = true,  // 현재 text가 보이는 상태인지에 대한 여부
    onPwdVisibleToggle: (() -> Unit)? = null,  // 눈 모양 아이콘 클릭 시 호출할 콜백 함수
) {
    BasicTextField(
        value = inputText,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Gray5, shape = RoundedCornerShape(6.dp))
            .padding(10.dp),
        textStyle = TextStyle.Default.copy(fontSize = 16.sp, color = Color.White),
        visualTransformation = if (isPwdField && !isPwdVisible) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    // 사용자의 입력이 없을 때 placeholder 노출
                    if (inputText.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 16.sp,
                            color = Gray3,
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.weight(1f))

                // 비밀번호 필드 여부
                if (isPwdField && onPwdVisibleToggle != null) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = onPwdVisibleToggle),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Icon(
                            imageVector = if (isPwdVisible) ImageVector.vectorResource(R.drawable.ic_eye_off) else ImageVector.vectorResource(
                                R.drawable.ic_eye_on
                            ),
                            contentDescription = "눈 아이콘",
                            tint = Gray3,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun InputFieldComponentPreview() {
    var idInputTest by remember { mutableStateOf("") }
    var pwdInputTest by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 아이디 inputfield인 경우
        InputFieldComponent(
            placeholder = stringResource(R.string.id_kor),
            inputText = idInputTest,
            onTextChanged = { idInputTest = it }
        )

        // 비밀번호 inputfield인 경우
        InputFieldComponent(
            placeholder = stringResource(R.string.pwd_kor),
            inputText = pwdInputTest,
            onTextChanged = { pwdInputTest = it },
            isPwdField = true,
            isPwdVisible = isPwdVisible,
            onPwdVisibleToggle = { isPwdVisible = !isPwdVisible }
        )
    }
}