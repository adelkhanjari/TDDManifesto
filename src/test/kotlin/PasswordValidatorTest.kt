import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class PasswordValidatorTest {
    @Test
    fun `should return error when given a small password`() {
        val validator = PasswordValidator()
        val input = "12345@A"
        val expectedExceptionMessage = "Password must be at least 8 characters."

        val exception = assertThrows<Exception> { validator.validate(input) }
        assertEquals(expectedExceptionMessage, exception.message)
    }

    @Test
    fun `should return error when given a password without 2 numbers`() {
        val validator = PasswordValidator()
        val input = "aaaaa@aaaA"
        val expectedExceptionMessage = "The password must contain at least 2 numbers."

        val exception = assertThrows<Exception> { validator.validate(input) }
        assertEquals(expectedExceptionMessage, exception.message)
    }

    @Test
    fun `should return multiple error when given a password with multiple errors`() {
        val validator = PasswordValidator()
        val input = "aaa"
        val expectedExceptionMessage = "Password must be at least 8 characters." + "\n" +
                "The password must contain at least 2 numbers." + "\n" +
                "Password must contain at least one capital letter." + "\n" +
                "Password must contain at least one special character."

        val exception = assertThrows<Exception> { validator.validate(input) }
        assertEquals(expectedExceptionMessage, exception.message)
    }

    @Test
    fun `should return error when given a password without capital letter`() {
        val validator = PasswordValidator()
        val input = "1234567@"
        val expectedExceptionMessage = "Password must contain at least one capital letter."

        val exception = assertThrows<Exception> { validator.validate(input) }
        assertEquals(expectedExceptionMessage, exception.message)
    }

    @Test
    fun `should return error when given a password without special character`() {
        val validator = PasswordValidator()
        val input = "1234567A"
        val expectedExceptionMessage = "Password must contain at least one special character."

        val exception = assertThrows<Exception> { validator.validate(input) }
        assertEquals(expectedExceptionMessage, exception.message)
    }
}