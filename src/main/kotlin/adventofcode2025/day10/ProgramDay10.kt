package adventofcode2025.day10

import common.Program

class ProgramDay10(brutInputs: List<String>, private val debug: Boolean = false) : Program {
    private val machines = brutInputs.map { Machine.build(it) }

    override fun part1(): String {
        return machines.sumOf {
            it.pushButtonsToTurnLights()
        }.toString()
    }

    override fun part2(): String {
        var counter = 0
        var time = System.currentTimeMillis()
        return machines.sumOf {
            it.pushButtonsToIncreaseCounter().also {
                val duration = (System.currentTimeMillis() - time) / 1000
                println("${counter++} - $it - ${duration}s")
                time = System.currentTimeMillis()
            }
        }.toString()
    }


}

data class Machine(val patternLight: List<Int>, val buttons: List<Button>, val joltages: List<Int>) {
    val nbMaxPushPerButon = buttons.associateWith { it.lights.minOf { joltages[it] } }

    fun pushButtonsToTurnLights(): Int {
        (1..buttons.size).forEach { nbPushMax ->
            searchBestPattern(emptyList(), remainingButtons = buttons, nbPushMax).let {
                if (it != null) {
                    return it
                }
            }
        }
        error("PROUT")
    }

    fun pushButtonsToIncreaseCounter(): Int {
        var map = mutableMapOf<Int, Int>()
        var nbPush = 0

        joltages.forEachIndexed { i, j ->
            map[i] = 0
        }


        searchBestPatternToIncrease(map, nbPush, buttons).let {
            if (it != null) {
                return it
            }
        }

        error("PROUT")
    }

    fun possibleValuesByButton(
        lightValue: Int,
        buttonsToPush: List<Button>,
        maxPushByButton: Map<Button, Int>,
        result: Map<Button, Int>,
    ): List<Map<Button, Int>>? {
        if (lightValue == 0) return listOf(result)
        if(buttonsToPush.isEmpty()) return null
        val button = buttonsToPush.first()
        val maxPush = maxPushByButton[button]!!

        if (buttonsToPush.size == 1) {
            if (lightValue > maxPush) return null
            return listOf(result.plus(Pair(button, lightValue)))
        }

        val nbPush = minOf(lightValue, maxPush)
        return (0..nbPush).mapNotNull { value ->
            possibleValuesByButton(
                lightValue - value, buttonsToPush.minus(button), maxPushByButton, result.plus(Pair(button, value))
            )
        }.flatten()
    }

    fun searchBestPatternToIncrease(
        currentJoltagesInitial: Map<Int, Int>,
        nbPushInitial: Int,
        remainingButtons: List<Button>,
    ): Int? {
        if (currentJoltagesInitial.anyUp()) {
            return null
        }
        if (currentJoltagesInitial.isEqualsToMain()) {
            return nbPushInitial
        }

        val remains = currentJoltagesInitial.remain()
        val lightToManage = remains.minBy { remain -> remainingButtons.count { remain.key in it.lights } }
        val buttonsToPush = remainingButtons.filter { lightToManage.key in it.lights }
        if (buttonsToPush.isEmpty()) return null

        val possiblesValues = possibleValuesByButton(
            joltages[lightToManage.key] - lightToManage.value, buttonsToPush, nbMaxPushPerButon,
            buttonsToPush.associateWith { 0 })

        return possiblesValues?.mapNotNull { buttonsPushed ->
            var newNbPush = nbPushInitial
            newNbPush += buttonsPushed.values.sum()
            var newJoltages = currentJoltagesInitial
            buttonsPushed.forEach {
                newJoltages = it.key.pushJoltage(newJoltages, it.value)
            }
            searchBestPatternToIncrease(newJoltages, newNbPush, remainingButtons.minus(buttonsToPush))
        }?.minOrNull()
    }

    fun searchBestPattern(currentLights: List<Int>, remainingButtons: List<Button>, nbPushMax: Int): Int? {
        val nbPush = buttons.size - remainingButtons.size
        if (nbPush >= nbPushMax) return null
        if (currentLights.isEqualsToMain().isEmpty()) {
            return nbPush
        }
        if (remainingButtons.isEmpty()) return null

        return remainingButtons.mapNotNull { button ->
            val newLights = button.pushLight(currentLights)
            searchBestPattern(newLights, remainingButtons.minus(button), nbPushMax).also {
                if (it != null) return it
            }
        }.minOrNull()
    }

    fun List<Int>.isEqualsToMain() = this.subtract(patternLight.toSet()) + patternLight.subtract(this.toSet())
    fun Map<Int, Int>.isEqualsToMain() = this.all { (index, value) -> joltages[index] == value }
    fun Map<Int, Int>.anyUp() = this.any { (index, value) -> value > joltages[index] }
    fun Map<Int, Int>.remain() = this.filter { (index, value) -> value != joltages[index] }

    companion object {
        fun build(line: String): Machine {
            val groups = line.split(" ")
            val pattern = groups.first { it.startsWith("[") }.let {
                it.substring(1, it.length - 1).split("").filter { it.isNotEmpty() }.mapIndexedNotNull { i, c ->
                    i.takeIf { c == "#" }
                }
            }

            val buttons = groups.filter { it.startsWith("(") }.map {
                it.substring(1, it.length - 1).split(",").map { it.toInt() }
            }.sortedBy { it.size }

            val joltages = groups.first { it.startsWith("{") }.let {
                it.substring(1, it.length - 1).split(",").map { it.toInt() }
            }

            return Machine(
                pattern,
                buttons.map { Button(it) },
                joltages
            )
        }
    }
}

data class Button(val lights: List<Int>) {
    fun pushLight(currentLights: List<Int>): List<Int> {
        val newLights = currentLights.toMutableList()
        lights.forEach { light ->
            if (!newLights.contains(light)) newLights.add(light)
            else if (newLights.contains(light)) newLights.remove(light)
        }
        return newLights
    }

    fun pushJoltage(
        currentJoltages: Map<Int, Int>,
//        nbPushByButton: MutableMap<Button, Int>,
        nbPush: Int = 1
    ): Map<Int, Int> {
        val newJoltages = currentJoltages.toMutableMap()
        lights.forEach { currentJoltage ->
            newJoltages[currentJoltage] = newJoltages[currentJoltage]?.plus(nbPush) ?: nbPush
        }
//        nbPushByButton[this] = nbPushByButton[this]?.let { it + nbPush } ?: nbPush
        return newJoltages
    }

    fun canPush(nbPush: Int, nbPushByButton: Map<Button, Int>, nbMaxPushByButton: Map<Button, Int>): Boolean {
        return true
        return nbPushByButton[this]!!.let {
            it + nbPush <= nbMaxPushByButton[this]!!
        }
    }
}
