package org.sopt.at.ui.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.ui.common.component.TvingTopAppBarComponent
import org.sopt.at.ui.common.navigation.NavRoutes
import org.sopt.at.ui.home.component.contentComponent
import org.sopt.at.ui.home.viewmodel.ContentViewModel
import org.sopt.at.ui.theme.TvingTheme.colors
import org.sopt.at.ui.theme.TvingTheme.typography

@Composable
fun HomeScreen(
    viewModel: ContentViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ) {
        // 상단바
        TvingTopAppBarComponent(
            rightFirstIconId = R.drawable.ic_baseline_connected_tv_24,
            onRightFirstIconClicked = { /* TODO: 아이콘 클릭 시 처리 */ },
            rightSecondIconId = R.drawable.img_profile,
            onRightSecondIconClicked = { navController.navigate(NavRoutes.Mypage.route) }
        )

        // 상단바 제외한 부분 상하 스크롤 되도록
        LazyColumn {
            item {
                // 최상단 배너뷰
                contentComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(460.dp),
                    contentList = viewModel.bannerContents
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 오늘의 티빙 top 20
                Text(
                    text = stringResource(R.string.today_top_20_contents),
                    style = typography.subtitle_SB,
                    color = colors.BasicWhite
                )
                contentComponent(
                    modifier = Modifier
                        .size(120.dp, 160.dp),
                    contentList = viewModel.top20Contents
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 지금 방영 중인 콘텐츠
                Text(
                    text = stringResource(R.string.now_on_contents),
                    style = typography.subtitle_SB,
                    color = colors.BasicWhite
                )
                contentComponent(
                    modifier = Modifier
                        .size(120.dp, 160.dp),
                    contentList = viewModel.nowOnContents
                )
            }
        }
    }
}