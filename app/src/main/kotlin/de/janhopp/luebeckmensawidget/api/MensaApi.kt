package de.janhopp.luebeckmensawidget.api

import de.janhopp.luebeckmensawidget.api.model.Language
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.MensaDay
import de.janhopp.luebeckmensawidget.api.model.filterByDate
import de.janhopp.luebeckmensawidget.api.model.toApiResponse
import de.janhopp.luebeckmensawidget.api.model.toCodes
import de.janhopp.luebeckmensawidget.api.model.toMensaDays
import de.janhopp.luebeckmensawidget.utils.alsoThrow
import de.janhopp.luebeckmensawidget.utils.currentTime
import de.janhopp.luebeckmensawidget.utils.mensaApiFormat
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.util.Locale
import kotlin.coroutines.cancellation.CancellationException

class MensaApi(
    private val client: HttpClient = HttpClient(),
) {
    private val date: String
        get() = currentTime.mensaApiFormat

    private val lang: Language
        get() = if (Locale.getDefault().language == Language.English.code)
            Language.English
        else
            Language.German

    suspend fun getMealsToday(locations: Set<Location>): MensaDay? = runCatching {
        client.get("https://speiseplan.mcloud.digital/v2/meals?location=${locations.toCodes()}&date=$date&language=${lang.code}")
            .body<String>()
            .toApiResponse()
            .toMensaDays()
            .filterByDate(date)
    }.alsoThrow<_, CancellationException>() // needed for use of runCatching in coroutines
        .getOrNull()

    suspend fun getAllDaysMeals(): List<MensaDay> = runCatching {
        client.get("https://speiseplan.mcloud.digital/v2/meals")
            .body<String>()
            .toApiResponse()
            .toMensaDays()
    }.alsoThrow<_, CancellationException>() // needed for use of runCatching in coroutines
        .getOrDefault(emptyList())
}
