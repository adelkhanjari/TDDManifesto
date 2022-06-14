import Constants.cities

class CitySearchUtil {

    fun search(input: String): List<String> {
        if (input == "*")
            return cities

        if (input.length < 2) {
            return listOf()
        }

        val lowercaseInput = input.lowercase()
        return cities.filter { it.lowercase().contains(lowercaseInput) }
    }
}