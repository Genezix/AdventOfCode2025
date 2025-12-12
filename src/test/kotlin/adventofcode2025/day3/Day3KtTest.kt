package adventofcode2025.day3

import kotlin.test.assertEquals
import org.junit.Test

internal class Day3KtTest {
    private val program = ProgramDay3(
        brutInputs = listOf(
            "987654321111111",
            "811111111111119",
            "234234234234278",
            "818181911112111",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "357",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "3121910778619",
            actual = program.part2()
        )
    }
}
