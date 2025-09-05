package de.janhopp.luebeckmensawidget.widget

import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.utils.currentTime
import kotlinx.datetime.LocalDateTime

data class MensaWidgetState(
    val config: MensaWidgetConfig,
    val mensaDay: MensaDay?,
    val refreshed: LocalDateTime = currentTime,
)
