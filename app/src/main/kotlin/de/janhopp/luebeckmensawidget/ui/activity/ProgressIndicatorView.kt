package de.janhopp.luebeckmensawidget.ui.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.janhopp.luebeckmensawidget.ui.components.shimmerBackground
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun ProgressIndicatorView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 4.dp),
    ) {
        val textHeight = 24.dp
        items(count = 8) {
            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(Random.nextInt(90..100).toFloat() / 100L)
                        .height(textHeight)
                        .shimmerBackground()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(Random.nextInt(90..100).toFloat() / 100L)
                        .height(textHeight)
                        .shimmerBackground()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.3f)
                        .height(textHeight)
                        .shimmerBackground()
                )
            }
        }
    }
}
