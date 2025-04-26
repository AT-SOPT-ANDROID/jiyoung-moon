package org.sopt.at.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.sopt.at.ui.home.viewmodel.Content
import org.sopt.at.ui.home.viewmodel.ContentViewModel

@Composable
fun contentComponent(
    modifier: Modifier,
    contentList: List<Content>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(contentList.size) { index ->
            contentItemComponent(
                modifier = modifier,
                content = contentList[index]
            )
        }
    }
}

// content 1개에 대한 컴포저블 함수
@Composable
fun contentItemComponent(
    modifier: Modifier,
    content: Content,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        if (content.ranking != 0) {
            Text(
                text = content.ranking.toString(),
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        AsyncImage(
            model = content.imgUrl,
            modifier = modifier,
            contentScale = ContentScale.Crop,
            contentDescription = "content image"
        )
    }
}