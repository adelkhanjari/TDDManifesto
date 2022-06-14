import kotlin.test.Test
import kotlin.test.assertEquals

class FizzBuzzTest {

    @Test
    fun `should return string value of integer when given an integer as input`() {
        val fizzBuzz = FizzBuzz()
        val input = 19
        val expectedOutput = "19"

        val output = fizzBuzz.fizzBuzz(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return Fizz when given an integer that is multiple of three`() {
        val fizzBuzz = FizzBuzz()
        val input = 9
        val expectedOutput = "Fizz"

        val output = fizzBuzz.fizzBuzz(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return Buzz when given an integer that is multiple of five`() {
        val fizzBuzz = FizzBuzz()
        val input = 10
        val expectedOutput = "Buzz"

        val output = fizzBuzz.fizzBuzz(input)

        assertEquals(expectedOutput, output)
    }

    @Test
    fun `should return FizzBuzz when given an integer that is multiple of three and five both`() {
        val fizzBuzz = FizzBuzz()
        val input = 15
        val expectedOutput = "FizzBuzz"

        val output = fizzBuzz.fizzBuzz(input)

        assertEquals(expectedOutput, output)
    }
}