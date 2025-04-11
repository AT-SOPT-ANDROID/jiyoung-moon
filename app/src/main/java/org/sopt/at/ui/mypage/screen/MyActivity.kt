package org.sopt.at.ui.mypage.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.common.component.ButtonComponent
import org.sopt.at.ui.common.component.TopAppBarComponent
import org.sopt.at.ui.onboarding.screen.SignInActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.ui.theme.Gray100

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                MyScreen(
                    profileId = "test"  // TODO: 회원가입 시 작성한 아이디 표시하기
                )
            }
        }
    }
}

@Composable
fun MyScreen(
    profileId: String
) {
    val context = LocalContext.current
    val intent = Intent(context, SignInActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
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
                contentDescription = "프로필 이미지",
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = profileId,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // '로그아웃' btn
        ButtonComponent(
            containerColor = Color.Black,
            contentColor = Gray100,
            text = stringResource(R.string.logout),
            onClick = { context.startActivity(intent) },
            strokeColor = Gray100
        )
    }
}

@Preview
@Composable
fun MyScreenPreview() {
    MyScreen(
        profileId = "홍길동"
    )
}