package d05

import readString

data class Action(
    val size: Int,
    val from: Int,
    val to: Int,
)

fun main() {
    val input = readString("d05/Day05")
        .split("\n\n")
    val (crates, instructions) = input

    val actions = instructions.split(" ", "\n")
        .filter {
            it !in listOf("move", "from", "to")
        }.chunked(3) { (s, f, t) ->
            Action(s.toInt(), f.toInt(), t.toInt())
        }

    val crateMap = crates
        .filter { it.isDigit() }
        .associate { it.digitToInt() to "" }
        .toMutableMap()

    val lines = crates.lines()
    for (i in lines.size - 2 downTo 0) {
        var c = 1
        repeat(crateMap.size) {
            val c1 = lines[i].getOrNull(c)
            if (c1 != null && c1.toString().isNotBlank()) {
                crateMap[it + 1] = crateMap[it + 1] + c1
            }
            c += 4
        }
    }

    fun part1(): String {
        val map = crateMap.toMutableMap()
        actions.forEach { (size, from, to) ->
            val fromWord = map[from]!!.toMutableList()
            var moved = ""
            repeat(size) {
                fromWord.removeLastOrNull()?.let { moved += it }
            }
            map[to] = map[to] + moved
            map[from] = fromWord.joinToString("")
        }
        return map.values.mapNotNull { it.lastOrNull() }.joinToString("")
    }

    fun part2(): String {
        val map = crateMap.toMutableMap()
        actions.forEach { (size, from, to) ->
            val s = map[from]!!
            val i = s.length - size
            val fromWord = s.substring(i, s.length)
            map[to] = map[to] + fromWord
            map[from] = s.substring(0, i)
        }
        return map.values.mapNotNull { it.lastOrNull() }.joinToString("")
    }

    println(part1())
    println(part2())
}