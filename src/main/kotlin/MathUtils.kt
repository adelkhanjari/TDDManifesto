object MathUtils {
    fun isMultipleOfThree(input: Int) = input % 3 == 0

    fun isMultipleOfFive(input: Int) = input % 5 == 0

    fun isMultipleOfThreeAndFiveBoth(input: Int) =
        isMultipleOfThree(input) && isMultipleOfFive(input)
}