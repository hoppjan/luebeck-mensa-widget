package de.janhopp.luebeckmensawidget.api

import android.util.Log
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.utils.alsoThrow
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaApiFormat
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlin.coroutines.cancellation.CancellationException

class MensaApi(
    private val client: HttpClient = HttpClient(),
) {
    private val date: String
        get() = currentTime.mensaApiFormat

    suspend fun getMealsToday(): List<MensaDay> = runCatching {
        client.get("https://speiseplan.mcloud.digital/meals?date=$date")
            .body<String>()
            .let { days -> MensaJson.decodeFromString<List<MensaDay>>(days) }
    }.alsoThrow<_, CancellationException>() // needed for use of runCatching in coroutines
        .getOrDefault(listOf())
}
