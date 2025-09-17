package de.janhopp.luebeckmensawidget.api.model

import org.junit.Test
import org.junit.Assert.*

class GroupedPricesTest {
    
    @Test
    fun `formatPrice should format regular price correctly`() {
        val regularPrice = 3.50f
        val result = regularPrice.formatPrice()
        
        assertEquals("3.50 €", result)
    }
    
    @Test
    fun `formatPriceOrRequest should return empty string for zero price`() {
        val zeroPrice = 0.0f
        val result = zeroPrice.formatPriceOrRequest()
        
        assertEquals("", result)
    }
    
    @Test
    fun `formatPriceOrRequest should format regular price correctly`() {
        val regularPrice = 4.25f
        val result = regularPrice.formatPriceOrRequest()
        
        assertEquals("4.25 €", result)
    }
    
    @Test
    fun `GroupedPrices with zero values should return empty string`() {
        val zeroPrices = GroupedPrices(
            students = 0.0f,
            employees = 0.0f,
            guests = 0.0f
        )
        
        val studentPrice = zeroPrices.getFor(PriceGroup.Students).formatPriceOrRequest()
        assertEquals("", studentPrice)
        
        val employeePrice = zeroPrices.getFor(PriceGroup.Employees).formatPriceOrRequest()
        assertEquals("", employeePrice)
        
        val guestPrice = zeroPrices.getFor(PriceGroup.Guests).formatPriceOrRequest()
        assertEquals("", guestPrice)
    }
    
    @Test
    fun `GroupedPrices with mixed values should handle both zero and non-zero correctly`() {
        val mixedPrices = GroupedPrices(
            students = 0.0f,
            employees = 3.50f,
            guests = 4.25f
        )
        
        val studentPrice = mixedPrices.getFor(PriceGroup.Students).formatPriceOrRequest()
        assertEquals("", studentPrice)
        
        val employeePrice = mixedPrices.getFor(PriceGroup.Employees).formatPriceOrRequest()
        assertEquals("3.50 €", employeePrice)
        
        val guestPrice = mixedPrices.getFor(PriceGroup.Guests).formatPriceOrRequest()
        assertEquals("4.25 €", guestPrice)
    }
    
    @Test
    fun `UI display logic should handle all combinations correctly`() {
        // Simulate the UI logic from the views
        fun createDisplayText(price: String, location: String): String {
            return when {
                price.isNotEmpty() && location.isNotEmpty() -> "$price | $location"
                price.isNotEmpty() -> price
                location.isNotEmpty() -> location
                else -> ""
            }
        }
        
        // Test regular price scenarios
        assertEquals("3.50 €", createDisplayText("3.50 €", ""))
        assertEquals("3.50 € | Mensa Lübeck", createDisplayText("3.50 €", "Mensa Lübeck"))
        
        // Test zero price scenarios (Bits & Bytes case)
        assertEquals("", createDisplayText("", ""))
        assertEquals("Bits + Bytes", createDisplayText("", "Bits + Bytes"))
        
        // Verify the actual fix works
        val zeroPrices = GroupedPrices(0.0f, 0.0f, 0.0f)
        val emptyPrice = zeroPrices.getFor(PriceGroup.Students).formatPriceOrRequest()
        
        assertEquals("", createDisplayText(emptyPrice, ""))
        assertEquals("Bits + Bytes", createDisplayText(emptyPrice, "Bits + Bytes"))
    }
}
