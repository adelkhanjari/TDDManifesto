class Account(
    val accountNumber: Int,
    val balanceManager: AbsBalanceManager,
    val statementManager: AbsStatementManager,
    val statementPrinter: AbsStatementPrinter
) {
    fun deposit(amount: Int) {
        balanceManager.increaseBalance(accountNumber, amount)
    }

    fun withdraw(amount: Int) {
        if (balanceManager.getBalance(accountNumber) < amount)
            throw Exception("Insufficient balance.")
        balanceManager.decreaseBalance(accountNumber, amount)
    }

    fun printStatement() {
        val statements = statementManager.getStatements(accountNumber)
        statements.forEach {
            statementPrinter.print(it)
        }
    }
}