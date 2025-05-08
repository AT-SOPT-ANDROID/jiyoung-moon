package org.sopt.at.ui.common.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.ui.theme.TvingTheme.colors

@Composable
fun BottomNavBar(navController: NavController) {
    val items = NavBarItems.BarItems
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route  // 현재 위치해있는 route

    Surface(
        color = colors.BasicBlack,
        modifier = Modifier.navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 8.dp)
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route  // 보여줘야할 페이지(route)랑 현재 route가 같은지 여부

                Column(
                    modifier = Modifier
                        .weight(1F)
                        .clickable {
                            if (!selected) {
                                navController.navigate(item.route)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(id = item.imageId),
                        contentDescription = item.title,
                        tint = if (selected) colors.BasicWhite else colors.Gray1
                    )
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = if (selected) colors.BasicWhite else colors.Gray1
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomNavBarPreview() {
    val navController = rememberNavController()
    BottomNavBar(navController)
}