package adventofcode2025.day2

import common.Program

class ProgramDay2(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val ranges = brutInputs.first().split(",").map {
        it.split("-").let { LongRange(it[0].toLong(), it[1].toLong()) }
    }

    override fun part1(): String = ranges.flatMap { it.getAllSymetricsValue() }.sum().toString()

    override fun part2(): String = ranges.flatMap { it.getInvalids() }.sum().toString()
}

private fun LongRange.getInvalids(): List<Long> {
    return this.mapNotNull {
        it.takeIf {
            it.toString().let { stringValue ->
                (stringValue.length % 2 == 0 &&
                        stringValue.take((stringValue.length / 2)) == stringValue.takeLast(stringValue.length / 2)) ||
                        stringValue.isRepeated()
            }
        }
    }
}

private fun String.isRepeated(repeatShema: Int = 2): Boolean {
    if(repeatShema > length) return false
    if(this.length % repeatShema != 0) return isRepeated(repeatShema + 1)
    val size = length / repeatShema
    if(this.replace(this.take(size), "").isEmpty()) return true
    return isRepeated(repeatShema + 1)
}

private fun LongRange.getAllSymetricsValue(): List<Long> {
    return this.mapNotNull {
        it.takeIf {
            it.toString().let { stringValue ->
                stringValue.length % 2 == 0 &&
                        stringValue.take((stringValue.length / 2)) == stringValue.takeLast(stringValue.length / 2)
            }
        }
    }
}
