import io.mockk.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class AccountTest {

    private val accountNumber = 9

    @Test
    fun `should increase balance when deposit is made to an account`() {
        val mockedBalanceManager = mockk<AbsBalanceManager>()
        val amount = 1000

        every { mockedBalanceManager.increaseBalance(accountNumber, amount) } just runs

        val account = Account(accountNumber, mockedBalanceManager, mockk(), mockk())

        account.deposit(amount)

        verify(exactly = 1) {
            mockedBalanceManager.increaseBalance(accountNumber, amount)
        }
    }

    @Test
    fun `should validate then decrease balance when withdraw is made to an account`() {
        val mockedBalanceManager = mockk<AbsBalanceManager>()
        val amount = 1000

        every { mockedBalanceManager.decreaseBalance(accountNumber, amount) } just runs
        every { mockedBalanceManager.getBalance(accountNumber) } returns amount + 1

        val account = Account(accountNumber, mockedBalanceManager, mockk(), mockk())

        account.withdraw(amount)

        verify(exactly = 1) {
            mockedBalanceManager.getBalance(accountNumber)
            mockedBalanceManager.decreaseBalance(accountNumber, amount)
        }
    }

    @Test
    fun `should throw exception when withdraw is made to an account with an insufficient balance`() {
        val mockedBalanceManager = mockk<AbsBalanceManager>()
        val amount = 1000

        every { mockedBalanceManager.decreaseBalance(accountNumber, amount) } just runs
        every { mockedBalanceManager.getBalance(accountNumber) } returns amount - 1

        val account = Account(accountNumber, mockedBalanceManager, mockk(), mockk())

        val exception = assertThrows<Exception> { account.withdraw(amount) }
        assertEquals("Insufficient balance.", exception.message)

        verify(exactly = 1) {
            mockedBalanceManager.getBalance(accountNumber)
        }

        verify(exactly = 0) {
            mockedBalanceManager.decreaseBalance(mockk(), mockk())
        }
    }

    @Test
    fun `should print statement when printStatement function of an account is called`() {
        val mockedStatementManager = mockk<AbsStatementManager>()
        val mockedStatementPrinter = mockk<AbsStatementPrinter>()

        every { mockedStatementManager.getStatements(accountNumber) } returns listOf(
            AccountStatement("10/04/2014", 500, 1400),
            AccountStatement("02/04/2014", -100, 900),
            AccountStatement("01/04/2014", 1000, 1000)
        )

        every { mockedStatementPrinter.print(any()) } just runs

        val account = Account(accountNumber, mockk(), mockedStatementManager, mockedStatementPrinter)

        account.printStatement()

        verify(exactly = 1) {
            mockedStatementManager.getStatements(accountNumber)
        }

        verify(exactly = 3) {
            mockedStatementPrinter.print(any())
        }
    }
}