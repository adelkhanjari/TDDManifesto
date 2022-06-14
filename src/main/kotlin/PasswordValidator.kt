class PasswordValidator {
    fun validate(input: String): Boolean {
        val exceptionMessages = mutableListOf<String>()

        if (!hasValidLength(input)) {
            exceptionMessages.add("Password must be at least 8 characters.")
        }

        if (!hasTwoNumbers(input)) {
            exceptionMessages.add("The password must contain at least 2 numbers.")
        }

        if (!hasCapitalLetter(input)) {
            exceptionMessages.add("Password must contain at least one capital letter.")
        }

        if (!hasSpecialCharacter(input)) {
            exceptionMessages.add("Password must contain at least one special character.")
        }

        if (exceptionMessages.isNotEmpty()) {
            throw Exception(exceptionMessages.joinToString("\n"))
        }

        return true
    }

    private fun hasSpecialCharacter(input: String) = input.any { !it.isLetterOrDigit() }

    private fun hasCapitalLetter(input: String) = input.any { it.isUpperCase() }

    private fun hasValidLength(input: String) = input.length >= 8

    private fun hasTwoNumbers(input: String) = input.filter { it.isDigit() }.length >= 2
}
