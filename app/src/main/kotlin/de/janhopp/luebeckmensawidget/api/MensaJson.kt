package de.janhopp.luebeckmensawidget.api

import kotlinx.serialization.json.Json

val MensaJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
}
