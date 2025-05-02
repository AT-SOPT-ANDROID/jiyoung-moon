package org.sopt.at.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.ui.theme.BasicBlack
import org.sopt.at.ui.theme.BasicWhite
import org.sopt.at.ui.theme.Gray3
import org.sopt.at.ui.theme.Gray4

@Composable
fun ButtonComponent(
    containerColor: Color,  // 배경 색
    contentColor: Color,  // 텍스트 색
    strokeColor: Color? = null,  // 테두리 색
    text: String,
    onClick: () -> Unit
) {
    var modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)

    if (strokeColor != null) {
        modifier = modifier.border(1.dp, strokeColor, shape = RoundedCornerShape(6.dp))
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun ButtonComponentPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BasicBlack)
            .padding(20.dp)
    ) {
        // 테두리가 없는 경우
        ButtonComponent(
            containerColor = Gray4,
            contentColor = Gray3,
            text = stringResource(R.string.login_action),
            onClick = { /* TODO: 버튼 클릭 시 처리 */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 테두리가 있는 경우
        ButtonComponent(
            containerColor = BasicBlack,
            contentColor = Gray3,
            text = stringResource(R.string.next),
            onClick = { /* TODO: 버튼 클릭 시 처리 */ },
            strokeColor = Gray3
        )
    }
}