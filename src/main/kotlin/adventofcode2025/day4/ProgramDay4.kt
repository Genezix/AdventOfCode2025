package adventofcode2025.day4

import common.Grid2DWith8Neighbors
import common.Position2D
import common.Program

class ProgramDay4(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val grid = brutInputs.let {
        Grid2DWith8Neighbors.build(it)
    }

    override fun part1(): String = grid.positions
        .filter { it.value == '@' }
        .count { it.neighbors.count { it.value == '@' } < 4 }.toString()

    override fun part2(): String = grid.positions
        .filter { it.value == '@' }
        .removePaperRolls(0)
        .toString()

    fun List<Position2D>.removePaperRolls(nbRemoved: Int): Int {
        val paperRollsToRemove =
            this.filter { it.neighbors.count { it.value == '@' } < 4 }
                .also { it.forEach { it.value = '.' } }

        if (paperRollsToRemove.isEmpty()) return nbRemoved

        return this.filter { it.value == '@' }.removePaperRolls(nbRemoved + paperRollsToRemove.size)
    }
}
