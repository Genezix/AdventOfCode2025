package adventofcode2025.day6

import common.Program
import common.multiply
import kotlin.collections.mapIndexed
import kotlin.text.toLong

class ProgramDay6(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val operationsPart1 = brutInputs.map { it.split(" ").filter { it.isNotBlank() } }.let { lines ->
        val operators = lines.last()
        val numbers = lines.take(lines.size - 1)
        operators.mapIndexed { index, operator ->
            Pair(operator.first(), numbers.map { it[index].toLong() })
        }
    }

    private val operationsPart2 = brutInputs.let { lines ->
        val operators = lines.last()
        val numbers = lines.take(lines.size - 1)

        var currentOperator = ' '
        var currentNumbers = mutableListOf<Long>()
        val operations = mutableListOf<Pair<Char, List<Long>>>()
        operators.forEachIndexed { index, operator ->
            if (operator != ' ') {
                currentOperator = operator
                currentNumbers = mutableListOf()
            }

            val isEmptyColumn = (operator == ' ' && numbers.all { it[index] == ' ' })

            if(!isEmptyColumn) {
                numbers
                    .map { it[index] }
                    .filter { it != ' ' }
                    .joinToString("")
                    .toLong()
                    .let { currentNumbers.add(it) }
            }

            if (isEmptyColumn || index == operators.length - 1) {
                operations.add(Pair(currentOperator, currentNumbers))
            }
        }
        operations.toList()
    }

    override fun part1(): String = operationsPart1.mySum().toString()

    override fun part2(): String = operationsPart2.mySum().toString()

    fun List<Pair<Char, List<Long>>>.mySum() = this.sumOf { operation ->
        val operator = operation.first
        val numbers = operation.second

        when (operator) {
            '+'-> numbers.sum()
            '*' -> numbers.multiply()
            else -> error("PROUT")
        }
    }
}
