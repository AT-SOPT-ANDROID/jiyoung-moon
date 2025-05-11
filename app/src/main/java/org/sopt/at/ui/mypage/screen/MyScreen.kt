package org.sopt.at.ui.mypage.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.ui.common.UserDataStore
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.common.networking.ResponseMyNicknameDto
import org.sopt.at.ui.common.networking.ServicePool
import org.sopt.at.ui.theme.TvingTheme.colors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MyScreen(navController: NavController) {
    val context = LocalContext.current
    var nickname by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        UserDataStore.getUserId(context).collect { userId ->
            if (userId != null) {
                ServicePool.userService.getMyNickname(userId)
                    .enqueue(object : Callback<ResponseMyNicknameDto> {
                        override fun onResponse(
                            call: Call<ResponseMyNicknameDto>,
                            response: Response<ResponseMyNicknameDto>
                        ) {
                            if (response.isSuccessful && response.body()?.success == true) {
                                nickname = response.body()?.data?.nickname ?: ""
                            }
                        }

                        override fun onFailure(call: Call<ResponseMyNicknameDto>, t: Throwable) {
                            Log.e("MyScreen", "내 닉네임 조회 api 실패: ${t.message}")
                        }
                    })
            } else {
                Log.e("MyScreen", "내 닉네임 조회 api 실패: userId 없음")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        // 상단바
        TopAppBarComponent(
            leftIconId = R.drawable.ic_round_arrow_back_ios_24,
            onLeftIconClicked = { /* TODO: 뒤로 가기 처리 */ },
            rightFirstIconId = R.drawable.ic_round_notifications_none_24,
            onRightFirstIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ },
            rightSecondIconId = R.drawable.ic_outline_settings_24,
            onRightSecondIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 프로필
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = stringResource(R.string.desc_profile_image),
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = nickname,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colors.BasicWhite
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // '로그아웃' btn
        ButtonComponent(
            containerColor = colors.BasicBlack,
            contentColor = colors.Gray3,
            text = stringResource(R.string.logout),
            onClick = { /* TODO: SignInScreen으로 이동 */ },
            strokeColor = colors.Gray3
        )
    }
}