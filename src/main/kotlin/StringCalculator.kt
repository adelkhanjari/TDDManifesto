class StringCalculator {

    fun add(input: String): Int {
        if (input.startsWith("//")) {
            val startIndexOfDelimiter = 2
            val endIndexOfDelimiter = input.indexOfFirst { it == '\n' }
            val delimiter = input.substring(startIndexOfDelimiter, endIndexOfDelimiter)
            val numbersSeparatedByDelimiter = input.substring(endIndexOfDelimiter + 1)

            return addWithDelimiter(numbersSeparatedByDelimiter, delimiter)
        }

        return addWithDelimiter(input)
    }

    private fun addWithDelimiter(input: String, delimiter: String? = null): Int {
        if (input.isEmpty()) {
            return 0
        }

        input.toIntOrNull()?.let {
            return validateNumber(it)
        }

        val exceptionMessages = mutableListOf<String>()
        val numbers = mutableListOf<Int>()

        val delimiters = if (delimiter != null) arrayOf(delimiter) else arrayOf(",", "\n")
        input.split(*delimiters).forEach { str ->
            val number = str.toIntOrNull()
            if (number != null) {
                numbers.add(validateNumber(number))
            } else {
                val wrongDelimiter = str.first { char -> !char.isDigit() }
                val wrongDelimiterPosition = input.indexOfFirst { char -> !char.isDigit() && char != delimiter?.first() }
                exceptionMessages.add("'$delimiter' expected but '$wrongDelimiter' found at position $wrongDelimiterPosition.")
                str.split(wrongDelimiter).forEach {
                    it.toIntOrNull()?.let { num ->
                        numbers.add(validateNumber(num))
                    }
                }
            }
        }

        val negativeNumbers = numbers.filter { it < 0 }
        if (negativeNumbers.isNotEmpty()) {
            exceptionMessages.add("Negative number(s) not allowed: ${negativeNumbers.joinToString(",")}")
        }

        if (exceptionMessages.isNotEmpty()) {
            throw Exception(exceptionMessages.reversed().joinToString("\n"))
        }

        return numbers.sum()
    }

    private fun validateNumber(number: Int): Int {
        return if (number > 1000) 0 else number
    }
}
