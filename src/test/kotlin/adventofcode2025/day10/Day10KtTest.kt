package adventofcode2025.day10

import kotlin.test.assertEquals
import org.junit.Test

internal class Day10KtTest {
    private val program = ProgramDay10(
        brutInputs = listOf(
            "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}",
            "[...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}",
            "[.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "7",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "33",
            actual = program.part2()
        )
    }
}
