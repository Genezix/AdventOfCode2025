package adventofcode2025.day1

import org.junit.Test
import kotlin.test.assertEquals

internal class Day1KtTest {
    private val program = ProgramDay1(
        brutInputs = listOf(
            "L68",
            "L30",
            "R48",
            "L5",
            "R60",
            "L55",
            "L1",
            "L99",
            "R14",
            "L82",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "3",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "6",
            actual = program.part2()
        )
    }
}
