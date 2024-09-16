package com.example.fitz

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs

fun Path.standardQuadTo(from: Offset, to: Offset){
    quadraticBezierTo(
        from.x,
        from.y,

        abs(from.x+to.x),
        abs(from.y+to.y),
    )
}