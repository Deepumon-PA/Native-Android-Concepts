package com.deepu.myandroidapp.feature_destinations.presentation.loginRegistration

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomBannerShape: Shape {


    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {


        return Outline.Generic(
            path = drawTicketPath(/*size = Size, cornerRadius = 12.0f*/)
        )
    }

     fun drawTicketPath(/*size: Size, cornerRadius: CornerRadius*/): Path{
        return Path().apply {
            addOval(
              Rect(
                  left = 12.0F,
                  right = 12.0F,
                  top =  12.0F,
                  bottom = 12.0F
              )
            )
        }

    }


}