package de.janhopp.luebeckmensawidget.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import de.janhopp.luebeckmensawidget.R

object Icons {
    @Composable
    fun paintArrowBack() = painterResource(R.drawable.ic_arrow_back)
    @Composable
    fun paintClear() = painterResource(R.drawable.ic_clear)
    @Composable
    fun paintInfo() = painterResource(R.drawable.ic_info)
    @Composable
    fun paintSettings() = painterResource(R.drawable.ic_settings)

}
