package adventofcode2025.day5

import common.Program
import common.parseGroupsList

class ProgramDay5(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val input = brutInputs.parseGroupsList().let {
        Input(
            it.first().map { it.split("-").let { LongRange(it[0].toLong(), it[1].toLong()) } },
            it.last().map { it.toLong() })
    }

    override fun part1(): String = input.ingredientIds.count { id -> input.ranges.any { id in it } }.toString()


    override fun part2(): String {
        var counter: Long = 0
        input.ranges.forEachIndexed { index, range ->
            counter += if (index != 0) {
                var rangeList = listOf(range)
                (0 until index).forEach { oldIndex ->
                    rangeList = rangeList.flatMap { currentRange ->
                        currentRange.remove(input.ranges[oldIndex])
                    }
                }
                rangeList.sumOf { (it.endInclusive - it.start) + 1 }
            } else {
                (range.endInclusive - range.start) + 1
            }
        }
        return counter.toString()
    }
}

fun LongRange.remove(rangeToRemove: LongRange): List<LongRange> {
    if (this.first >= rangeToRemove.first && this.last <= rangeToRemove.last) return listOf()
    if (this.first > rangeToRemove.last || this.last < rangeToRemove.first) return listOf(this)

    return listOfNotNull(
        takeIf { this.start < rangeToRemove.start }?.let {
            LongRange(this.start, rangeToRemove.start - 1)
        },
        takeIf { this.endInclusive > rangeToRemove.endInclusive }?.let {
            LongRange(
                rangeToRemove.endInclusive + 1,
                this.endInclusive
            )
        }
    )
}

data class Input(val ranges: List<LongRange>, val ingredientIds: List<Long>)
