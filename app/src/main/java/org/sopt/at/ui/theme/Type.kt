package org.sopt.at.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.at.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val tvingFontBold = FontFamily(Font(R.font.pretendard_bold))
val tvingFontRegular = FontFamily(Font(R.font.pretendard_regular))
val tvingFontSemibold = FontFamily(Font(R.font.pretendard_semibold))

@Immutable
data class TvingTypography(
    val title_B: TextStyle,
    val subtitle_SB: TextStyle,
    val body_R: TextStyle,
    val btn_B: TextStyle,
    val caption_R: TextStyle
)

val defaultTvingTypography = TvingTypography(
    title_B = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    subtitle_SB = TextStyle(
        fontFamily = tvingFontSemibold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    body_R = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    btn_B = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    caption_R = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
)

val LocalTvingTypographyProvider = staticCompositionLocalOf { defaultTvingTypography }