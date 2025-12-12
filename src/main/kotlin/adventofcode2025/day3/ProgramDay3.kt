package adventofcode2025.day3

import common.Program

class ProgramDay3(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val lines = brutInputs.map { it.mapIndexed { index, value -> Pair(index, value.digitToInt()) } }

    override fun part1(): String = lines.sumOf { line ->
        getMax(line, 2, 0).toLong()
    }.toString()

    override fun part2(): String = lines.sumOf { line ->
        getMax(line, 12, 0).toLong()
    }.toString()

    fun getMax(line: List<Pair<Int, Int>>, nbBatteries: Int, startIndex: Int): String {
        if (nbBatteries == 0) return ""

        val result = line
            .takeLast(line.size - startIndex)
            .let { it.take(it.size - (nbBatteries - 1)) }
            .maxBy { it.second }

        return result.second.toString() + getMax(line, nbBatteries - 1, result.first + 1)
    }


    fun oldAlgoPart1() = lines.sumOf {
        val first = it.take(it.size - 1).maxBy { it.second }
        val second = it.takeLast(it.size - (first.first + 1)).maxBy { it.second }
        "${first.second}${second.second}".toInt()
    }.toString()

    fun oldAlgoPart2() = lines.sumOf { line ->
        val first = line
            .take(line.size - 11).maxBy { it.second }
        val bat2 = line
            .takeLast(line.size - (first.first + 1)).let { it.take(it.size - 10) }.maxBy { it.second }
        val bat3 = line
            .takeLast(line.size - (bat2.first + 1)).let { it.take(it.size - 9) }.maxBy { it.second }
        val bat4 = line
            .takeLast(line.size - (bat3.first + 1)).let { it.take(it.size - 8) }.maxBy { it.second }
        val bat5 = line
            .takeLast(line.size - (bat4.first + 1)).let { it.take(it.size - 7) }.maxBy { it.second }
        val bat6 = line
            .takeLast(line.size - (bat5.first + 1)).let { it.take(it.size - 6) }.maxBy { it.second }
        val bat7 = line
            .takeLast(line.size - (bat6.first + 1)).let { it.take(it.size - 5) }.maxBy { it.second }
        val bat8 = line
            .takeLast(line.size - (bat7.first + 1)).let { it.take(it.size - 4) }.maxBy { it.second }
        val bat9 = line
            .takeLast(line.size - (bat8.first + 1)).let { it.take(it.size - 3) }.maxBy { it.second }
        val bat10 = line
            .takeLast(line.size - (bat9.first + 1)).let { it.take(it.size - 2) }.maxBy { it.second }
        val bat11 = line
            .takeLast(line.size - (bat10.first + 1)).let { it.take(it.size - 1) }.maxBy { it.second }
        val bat12 = line
            .takeLast(line.size - (bat11.first + 1)).maxBy { it.second }
        "${first.second}${bat2.second}${bat3.second}${bat4.second}${bat5.second}${bat6.second}${bat7.second}${bat8.second}${bat9.second}${bat10.second}${bat11.second}${bat12.second}".toLong()
    }.toString()
}
