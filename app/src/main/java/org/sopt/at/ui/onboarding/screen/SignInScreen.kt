package org.sopt.at.ui.onboarding.screen

import android.widget.Toast
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.ui.common.UserDataStore
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.common.navigation.NavRoutes
import org.sopt.at.ui.common.networking.RequestSignInDto
import org.sopt.at.ui.common.networking.ResponseSignInDto
import org.sopt.at.ui.common.networking.ServicePool
import org.sopt.at.ui.onboarding.component.InputFieldComponent
import org.sopt.at.ui.theme.TvingTheme.colors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignInScreen(navController: NavController) {
    var idInputText by remember { mutableStateOf("") }
    var pwdInputText by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.BasicBlack)
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
            color = colors.BasicWhite
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
            containerColor = colors.Gray4,
            contentColor = colors.Gray3,
            text = stringResource(R.string.login_action),
            onClick = {
                val request = RequestSignInDto(
                    loginId = idInputText,
                    password = pwdInputText
                )

                ServicePool.userService.postSignIn(request)
                    .enqueue(object : Callback<ResponseSignInDto> {
                        override fun onResponse(
                            call: Call<ResponseSignInDto>,
                            response: Response<ResponseSignInDto>
                        ) {
                            if (response.isSuccessful && response.body()?.success == true) {
                                Toast.makeText(context, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                val userId = response.body()!!.data.userId
                                coroutineScope.launch {
                                    UserDataStore.saveUserId(context, userId)
                                }
                                navController.navigate(NavRoutes.Home.route)    // home 화면으로 이동
                            } else {
                                val message = response.body()?.message ?: "로그인에 실패했습니다."
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseSignInDto>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "네트워크 오류: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
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
                color = colors.Gray3,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            VerticalDivider(
                modifier = Modifier
                    .height(16.dp)
                    .padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = colors.Gray3
            )
            Text(  // 비밀번호 찾기
                text = stringResource(R.string.pwd_kor) + " " + stringResource(R.string.find),
                fontSize = 16.sp,
                color = colors.Gray3,
                modifier = Modifier.clickable { /* TODO: 텍스트 클릭 시 처리 */ }
            )
            VerticalDivider(
                modifier = Modifier
                    .height(16.dp)
                    .padding(horizontal = 8.dp),
                thickness = 1.dp,
                color = colors.Gray3
            )
            Text(  // 회원가입
                text = stringResource(R.string.signup),
                fontSize = 16.sp,
                color = colors.Gray3,
                modifier = Modifier.clickable {
                    navController.navigate(NavRoutes.SignUp.route)    // signup 화면으로 이동
                }
            )
        }
    }
}