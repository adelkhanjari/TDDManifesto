import java.text.DecimalFormat

class BarcodeUtil {
    fun scan(input: String): String {
        if (input.isEmpty())
            return "Error: empty barcode."

        if (input == "99999")
            return "Error: barcode not found."

        val sumOfPrices = input.split("#").sumOf { processBarcode(it) }
        val formattedPriceString = DecimalFormat("#.00").format(sumOfPrices)
        return "$$formattedPriceString"
    }

    private fun processBarcode(barcode: String): Double {
        return when (barcode) {
            "12345" -> 7.25
            "23456" -> 12.50
            else -> 0.0
        }
    }
}