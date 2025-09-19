package de.janhopp.luebeckmensawidget.ui.utils

import de.janhopp.luebeckmensawidget.api.model.GroupedPrices
import de.janhopp.luebeckmensawidget.api.model.Location
import de.janhopp.luebeckmensawidget.api.model.Meal
import de.janhopp.luebeckmensawidget.api.model.MensaLocation
import de.janhopp.luebeckmensawidget.api.model.PriceGroup
import org.junit.Assert.assertEquals
import org.junit.Test

fun formatMealInfo(price: Float, locations: Set<Location>) = Meal(
    name = "name",
    date = "date",
    price = GroupedPrices(price, 2f, 3f),
    vegan = false,
    vegetarian = true,
    location = MensaLocation(code = "HL_BB", name = "Bits + Bytes", city = "Lübeck"),
    allergens = emptyList(),
).formatMealInfo(priceGroup = PriceGroup.Students, locations = locations)

class FormatMealInfoTest {
    @Test
    fun `formatMealInfo with 2 locations and non-zero price should return both price and location`() {
        val actual = formatMealInfo(1f, setOf(Location.BitsAndBytes, Location.MensaLuebeck))
        assertEquals("1,00 € | Bits + Bytes", actual)
    }

    @Test
    fun `formatMealInfo with 1 location and non-zero price should return only price`() {
        val actual = formatMealInfo(1f, setOf(Location.BitsAndBytes))
        assertEquals("1,00 €", actual)
    }

    @Test
    fun `formatMealInfo with 2 locations and zero price should return only location`() {
        val actual = formatMealInfo(0f, setOf(Location.BitsAndBytes, Location.MensaLuebeck))
        assertEquals("Bits + Bytes", actual)
    }

    @Test
    fun `formatMealInfo with 1 location and zero price should return only question marks`() {
        val actual = formatMealInfo(0f, setOf(Location.BitsAndBytes))
        assertEquals("???", actual)
    }
}
