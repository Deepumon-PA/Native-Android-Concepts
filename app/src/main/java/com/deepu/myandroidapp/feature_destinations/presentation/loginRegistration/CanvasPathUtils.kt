package com.deepu.myandroidapp.feature_destinations.presentation.loginRegistration

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

/**
 * util for drawing bezier curve from one point to the other
 */
fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f, // to get a smoother curve provide values like this
        abs(from.y + to.y) / 2f
    )
}