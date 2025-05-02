package org.sopt.at.ui.history.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sopt.at.ui.theme.BasicBlack
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun HistoryScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BasicBlack),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "History screen",
            style = TvingTheme.typography.body_R,
            color = TvingTheme.colors.BasicWhite
        )
    }
}