package adventofcode2025.day2

import kotlin.test.assertEquals
import org.junit.Test

internal class Day2KtTest {
    private val program = ProgramDay2(
        brutInputs = listOf(
            "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124",
        ).map { it },
        debug = true
    )

    @Test
    fun part1() {
        assertEquals(
            expected = "1227775554",
            actual = program.part1()
        )
    }

    @Test
    fun part2() {
        assertEquals(
            expected = "4174379265",
            actual = program.part2()
        )
    }
}
