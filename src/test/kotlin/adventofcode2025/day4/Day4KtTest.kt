package adventofcode2025.day4

import kotlin.test.assertEquals
import org.junit.Test

internal class Day4KtTest {
    private val program = ProgramDay4(
        brutInputs = listOf(
            "..@@.@@@@.",
            "@@@.@.@.@@",
            "@@@@@.@.@@",
            "@.@@@@..@.",
            "@@.@@@@.@@",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "@.@@@.@@@@",
            ".@@@@@@@@.",
            "@.@.@@@.@.",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "13",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "43",
            actual = program.part2()
        )
    }
}
