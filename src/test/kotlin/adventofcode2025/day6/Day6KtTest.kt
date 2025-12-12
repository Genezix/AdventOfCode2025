package adventofcode2025.day6

import kotlin.test.assertEquals
import org.junit.Test

internal class Day6KtTest {
    private val program = ProgramDay6(
        brutInputs = listOf(
            "123 328  51 64 ",
            " 45 64  387 23 ",
            "  6 98  215 314",
            "*   +   *   +  ",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "4277556",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "3263827",
            actual = program.part2()
        )
    }
}
