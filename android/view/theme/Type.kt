package com.tobiasheymann.ep.view.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tobiasheymann.ep.R

val openSans = FontFamily(
    Font(R.font.open_sans_extrabold, FontWeight.ExtraBold),
    Font(R.font.open_sans_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.open_sans_bold, FontWeight.Bold),
    Font(R.font.open_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.open_sans_semibold, FontWeight.SemiBold),
    Font(R.font.open_sans_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.open_sans_medium, FontWeight.Medium),
    Font(R.font.open_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.open_sans_regular, FontWeight.Normal),
    Font(R.font.open_sans_light, FontWeight.Light),
    Font(R.font.open_sans_light_italic, FontWeight.Light, FontStyle.Italic)
)

val LightTypography = Typography(
    defaultFontFamily = openSans,
    h1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = TEXT_PRIMARY
    ),
    h2 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = TEXT_PRIMARY
    ),
    h3 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = PRIMARY_VARIABLE
    ),
    subtitle1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        color = TEXT_SECONDARY
    ),
    body1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = TEXT_PRIMARY
    ),
    body2 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = TEXT_SECONDARY
    )
)

val DarkTypography = Typography(
    defaultFontFamily = openSans,
    h1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = DARK_TEXT_PRIMARY
    ),
    h2 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = DARK_TEXT_PRIMARY
    ),
    h3 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        color = DARK_PRIMARY_VARIABLE
    ),
    subtitle1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        color = DARK_TEXT_SECONDARY
    ),
    body1 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = DARK_TEXT_PRIMARY
    ),
    body2 = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = DARK_TEXT_SECONDARY
    )
)