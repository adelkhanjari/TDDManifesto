class FizzBuzz {
    fun fizzBuzz(input: Int) = input.toFizzBuzzString()
}

private fun Int.toFizzBuzzString(): String {
    return if (MathUtils.isMultipleOfThreeAndFiveBoth(this)) {
        Constants.FIZZBUZZ
    } else if (MathUtils.isMultipleOfThree(this)) {
        Constants.FIZZ
    } else if (MathUtils.isMultipleOfFive(this)) {
        Constants.BUZZ
    } else {
        toString()
    }
}
