package de.janhopp.luebeckmensawidget.api

import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.utils.alsoThrow
import de.janhopp.luebeckmensawidget.utils.currentDay
import de.janhopp.luebeckmensawidget.utils.toMensaApiFormat
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class MensaApi(
    private val client: HttpClient = HttpClient(),
) {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val urlDay: String
        get() = currentDay.dayOfWeek.toMensaApiFormat()

    suspend fun getMealsToday(): List<MensaDay> = runCatching {
        client.get("https://speiseplan.mcloud.digital/meals?day=$urlDay&week=current")
            .body<String>()
            .let { days -> json.decodeFromString<List<MensaDay>>(days) }
    }.alsoThrow<_, CancellationException>() // needed for use of runCatching in coroutines
        .getOrDefault(listOf())
}
