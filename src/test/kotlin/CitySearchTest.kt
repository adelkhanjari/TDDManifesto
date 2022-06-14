import Constants.cities
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CitySearchTest {

    @Test
    fun `should return no result when given a search text with fewer than 2 characters`() {
        val searchUtil = CitySearchUtil()
        val input = "a"

        val results = searchUtil.search(input)

        assertTrue(results.isEmpty())
    }

    @Test
    fun `should return correct result when given a valid search text`() {
        val searchUtil = CitySearchUtil()
        val input = "Va"

        val results = searchUtil.search(input)

        assertTrue(results.size == 2)
        assertEquals("Valencia", results[0])
        assertEquals("Vancouver", results[1])
    }

    @Test
    fun `should return correct result when given a valid search text with insensitive characters`() {
        val searchUtil = CitySearchUtil()
        val input = "va"

        val results = searchUtil.search(input)

        assertTrue(results.size == 2)
        assertEquals("Valencia", results[0])
        assertEquals("Vancouver", results[1])
    }

    @Test
    fun `should return correct result when given a valid search text that is a part of city name`() {
        val searchUtil = CitySearchUtil()
        val input = "ape"

        val results = searchUtil.search(input)

        assertTrue(results.size == 1)
        assertEquals("Budapest", results[0])
    }

    @Test
    fun `should return all results when given a asterisk search text`() {
        val searchUtil = CitySearchUtil()
        val input = "*"

        val results = searchUtil.search(input)

        assertEquals(cities.size, results.size)
        assertTrue(results.all { cities.contains(it) })
        assertTrue(cities.all { results.contains(it) })
    }
}