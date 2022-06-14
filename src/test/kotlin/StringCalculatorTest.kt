import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class StringCalculatorTest {

    @Test
    fun `should return zero when given an empty string`() {
        val calculator = StringCalculator()
        val input = ""
        val expectedOutput = 0

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return integer value when given a string with only one integer`() {
        val calculator = StringCalculator()
        val input = "9"
        val expectedOutput = 9

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return sum of integer values when given a string with two comma separated integers`() {
        val calculator = StringCalculator()
        val input = "9,9"
        val expectedOutput = 18

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return sum of integer values when given a string with any number of comma separated integers`() {
        val calculator = StringCalculator()
        val input = "9,9,9,9,9"
        val expectedOutput = 45

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return sum of integer values when given a string with any number of comma or newline separated integers`() {
        val calculator = StringCalculator()
        val input = "9,9\n9,9\n9"
        val expectedOutput = 45

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should throw exception when given a separator ended string`() {
        val calculator = StringCalculator()
        val input = "9,9\n9,9\n9,"

        assertThrows<Exception> { calculator.add(input) }
    }

    @Test
    fun `should accept custom delimiter when given a delimiter started string`() {
        val calculator = StringCalculator()
        val input = "//A\n9A9A9A9A9"
        val expectedOutput = 45

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should throw exception when given a delimiter started string with wrong delimiter`() {
        val calculator = StringCalculator()
        val input = "//A\n9A9A9B9A9"

        val exception = assertThrows<Exception> { calculator.add(input) }
        assertEquals("'A' expected but 'B' found at position 5.", exception.message)
    }

    @Test
    fun `should throw exception when given a string that has negative numbers`() {
        val calculator = StringCalculator()
        val input = "//A\n9A-9A9A9A9"

        val exception = assertThrows<Exception> { calculator.add(input) }
        assertEquals("Negative number(s) not allowed: -9", exception.message)
    }

    @Test
    fun `should throw exception when given a string that has negative numbers and wrong delimiter`() {
        val calculator = StringCalculator()
        val input = "//A\n9A9B-9"

        val exception = assertThrows<Exception> { calculator.add(input) }
        assertEquals("Negative number(s) not allowed: -9" + "\n" + "'A' expected but 'B' found at position 3.", exception.message)
    }

    @Test
    fun `should ignore numbers bigger than 1000 when given a string with such number`() {
        val calculator = StringCalculator()
        val input = "9,1009"
        val expectedOutput = 9

        val output = calculator.add(input)

        assertEquals(expectedOutput, output)
    }
}