package d04

import readInput

fun main() {
    fun run(m1: String, m2: String, s1: String, s2: String) =
        s1.toInt() >= m1.toInt() && s2.toInt() <= m2.toInt() ||
                m1.toInt() >= s1.toInt() && m2.toInt() <= s2.toInt()

    fun run2(m1: String, m2: String, s1: String, s2: String) =
        (m1.toInt()..m2.toInt())
            .windowed(2, partialWindows = true)
            .any {
                it.intersect(
                    (s1.toInt()..s2.toInt())
                        .windowed(2, partialWindows = true)
                        .flatten()
                        .toSet()
                ).isNotEmpty()
            }

    fun run2Better(m1: String, m2: String, s1: String, s2: String) =
        s1.toInt() <= m2.toInt() && s2.toInt() >= m1.toInt()

    val input = readInput("d04/Day04").flatMap { it.split(",") }

    fun part1(): MutableList<String> {
        val result = mutableListOf<String>()
        for (i in input.indices step 2) {
            val (a, b) = input[i].split("-")
            val (c, d) = input[i + 1].split("-")
            if (run(a, b, c, d)) {
                result.add("${input[i]}, ${input[i + 1]}")
            }
        }
        return result
    }

    fun part2(): MutableList<String> {
        val result = mutableListOf<String>()
        for (i in input.indices step 2) {
            val (a, b) = input[i].split("-")
            val (c, d) = input[i + 1].split("-")
            if (run2Better(a, b, c, d)) {
                result.add("${input[i]}, ${input[i + 1]}")
            }
        }
        return result
    }

    println(part1().size)
    println(part2().size)
}