package adventofcode2025.day5

import kotlin.test.assertEquals
import org.junit.Test

internal class Day5KtTest {
    private val program = ProgramDay5(
        brutInputs = listOf(
            "3-5",
            "10-14",
            "16-20",
            "12-18",
            "",
            "1",
            "5",
            "8",
            "11",
            "17",
            "32",
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
            expected = "14",
            actual = program.part2()
        )
    }
}
