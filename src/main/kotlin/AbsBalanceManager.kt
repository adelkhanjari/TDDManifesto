interface AbsBalanceManager {
    fun increaseBalance(accountNumber: Int, amount: Int)
    fun decreaseBalance(accountNumber: Int, amount: Int)
    fun getBalance(accountNumber: Int): Int
}
