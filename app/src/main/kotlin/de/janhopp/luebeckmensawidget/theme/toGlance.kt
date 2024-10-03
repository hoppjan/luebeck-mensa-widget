package de.janhopp.luebeckmensawidget.theme

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.glance.ColorFilter
import androidx.glance.LocalContext
import androidx.glance.unit.ColorProvider

fun androidx.compose.ui.text.TextStyle.toGlance(): androidx.glance.text.TextStyle =
    androidx.glance.text.TextStyle(
        color = ColorProvider(color),
        fontStyle = fontStyle?.toGlance(),
        fontSize = fontSize.toGlance(),
        fontWeight = fontWeight?.toGlance(),
        textAlign = textAlign?.toGlance(),
        textDecoration = textDecoration?.toGlance(),
        fontFamily = fontFamily?.toGlance()
    )

fun androidx.compose.ui.text.font.FontStyle.toGlance(): androidx.glance.text.FontStyle =
    when (value) {
        1 -> androidx.glance.text.FontStyle.Italic
        else -> androidx.glance.text.FontStyle.Normal
    }

fun androidx.compose.ui.text.font.FontWeight.toGlance(): androidx.glance.text.FontWeight =
    when (weight) {
        in 700..1000 -> androidx.glance.text.FontWeight.Bold
        in 500..699 -> androidx.glance.text.FontWeight.Medium
        else -> androidx.glance.text.FontWeight.Normal
    }

fun androidx.compose.ui.text.style.TextAlign.toGlance(): androidx.glance.text.TextAlign? =
    when (toString()) {
        "Left" -> androidx.glance.text.TextAlign.Left
        "Right" -> androidx.glance.text.TextAlign.Right
        "Center" -> androidx.glance.text.TextAlign.Center
        "Start" -> androidx.glance.text.TextAlign.Start
        "End" -> androidx.glance.text.TextAlign.End
        else -> null
    }

fun androidx.compose.ui.text.style.TextDecoration.toGlance(): androidx.glance.text.TextDecoration =
    when (mask) {
        0x1 -> androidx.glance.text.TextDecoration.Underline
        0x2 -> androidx.glance.text.TextDecoration.LineThrough
        else -> androidx.glance.text.TextDecoration.None
    }

fun androidx.compose.ui.text.font.FontFamily.toGlance(): androidx.glance.text.FontFamily =
    when (this) {
        is androidx.compose.ui.text.font.GenericFontFamily ->
            when (name) {
                "cursive" -> androidx.glance.text.FontFamily.Cursive
                "serif" -> androidx.glance.text.FontFamily.Serif
                "monospace" -> androidx.glance.text.FontFamily.Monospace
                else -> androidx.glance.text.FontFamily.SansSerif
            }
        is androidx.compose.ui.text.font.SystemFontFamily ->
            androidx.glance.text.FontFamily.SansSerif
        else ->
            androidx.glance.text.FontFamily.SansSerif
    }

fun TextUnit.toGlance(): TextUnit = TextUnit(value, TextUnitType.Sp)

fun Color.toColorFilter() = ColorFilter.tint(ColorProvider(color = this))

@Composable
fun glanceString(@StringRes resId: Int, vararg formatArgs: Any): String =
    LocalContext.current.getString(resId, *formatArgs)
