package com.deepu.myandroidapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.deepu.myandroidapp.R

// Set of Material typography styles to start with

val CinderellaFonts = FontFamily(               // put your custom fonts here
    Font(R.font.cinderela_regular)
)

val josefinSansFonts = FontFamily(
    Font(R.font.josefinsans_light, weight = FontWeight.Light),
    Font(R.font.josefinsans_regular, weight = FontWeight.Normal),
    Font(R.font.josefinsans_medium, weight = FontWeight.Medium),
    Font(R.font.josefinsans_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = josefinSansFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),

    body2 = TextStyle(
        fontFamily = josefinSansFonts,
        fontWeight = FontWeight.Medium
    ),

    h1 = TextStyle(
        fontFamily = josefinSansFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        color = Color.White

    ),

    h2 = TextStyle(
        fontFamily = josefinSansFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.White
    ),

//     Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

