package adventofcode2025.day1

import common.Program
import kotlin.math.abs

class ProgramDay1(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val inputs = brutInputs.map {
        val letter = it.first()
        val number = it.substring(1).toInt()
        if (letter == 'R') number else -number
    }

    override fun part1(): String {
        var counterZero = 0

        inputs.fold(50) { value, newValue ->

            (((value + newValue).also { if (it % 100 == 0) counterZero++ } + 100) % 100)
        }

        return counterZero.toString()
    }

    override fun part2(): String {
        var counterZero = 0
        var currentValue = 50
        inputs.forEach { incr ->
            repeat(abs(incr)) {
                if (incr > 0) currentValue++ else currentValue--
                currentValue = (currentValue + 100) % 100;
                if (currentValue == 0) counterZero++
            }
        }
        return counterZero.toString()
    }
}
