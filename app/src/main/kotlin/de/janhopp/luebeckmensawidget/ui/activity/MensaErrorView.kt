package de.janhopp.luebeckmensawidget.ui.activity

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.janhopp.luebeckmensawidget.R

@Composable
fun MensaErrorView(
    @DrawableRes imageRes: Int = R.drawable.error,
    errorMessage: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        LazyColumn(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Image(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .size(64.dp),
                    painter = painterResource(id = imageRes),
                    contentDescription = errorMessage,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                )
            }
            item {
                Text(
                    text = errorMessage,
                    modifier = Modifier.padding(all = 8.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}