package de.janhopp.luebeckmensawidget.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

fun Typography(color: Color) = TextStyle(color = color)
    .let { Typography(it, it, it, it, it, it, it, it, it, it, it, it, it, it, it) }
