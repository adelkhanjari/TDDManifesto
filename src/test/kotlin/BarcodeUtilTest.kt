import kotlin.test.Test
import kotlin.test.assertEquals

class BarcodeUtilTest {

    @Test
    fun `should display correct price when given 12345 as barcode`() {
        val barcodeUtil = BarcodeUtil()
        val input = "12345"
        val expectedOutput = "$7.25"

        val output = barcodeUtil.scan(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should display correct price when given 23456 as barcode`() {
        val barcodeUtil = BarcodeUtil()
        val input = "23456"
        val expectedOutput = "$12.50"

        val output = barcodeUtil.scan(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should display not found error when given 99999 as barcode`() {
        val barcodeUtil = BarcodeUtil()
        val input = "99999"
        val expectedOutput = "Error: barcode not found."

        val output = barcodeUtil.scan(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should display empty error when given an empty barcode`() {
        val barcodeUtil = BarcodeUtil()
        val input = ""
        val expectedOutput = "Error: empty barcode."

        val output = barcodeUtil.scan(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should display sum of prices when given an comma separated command`() {
        val barcodeUtil = BarcodeUtil()
        val input = "12345#23456"
        val expectedOutput = "$19.75"

        val output = barcodeUtil.scan(input)

        assertEquals(expectedOutput, output)
    }
}