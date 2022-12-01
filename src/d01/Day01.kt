package d01

import readInput

fun main() {
    fun part1(day: String): Int? {
        var calories = mutableListOf<Int>()
        var sum = 0
        val (input, lastIndex) = readInput(day).let { it to it.lastIndex }
        for ((i, s) in input.withIndex()) {
            if (s.isEmpty()) {
                sum = maxOf(sum, calories.sum())
                calories = mutableListOf()
                continue
            }
            if (i == lastIndex) {
                calories.add(s.toInt())
                sum = maxOf(sum, calories.sum())
                break
            }
            calories.add(s.toInt())
        }
        return sum.takeIf { it > 0 }
    }

    fun part2(day: String): Int {
        var calories = mutableListOf<Int>()
        val (input, lastIndex) = readInput(day).let { it to it.lastIndex }
        val top3 = mutableListOf<Int>()
        for ((i, s) in input.withIndex()) {
            if (s.isEmpty()) {
                top3.add(calories.sum())
                calories = mutableListOf()
                continue
            }
            if (i == lastIndex) {
                calories.add(s.toInt())
                top3.add(calories.sum())
                break
            }
            calories.add(s.toInt())
        }
        return top3.sortedDescending().take(3).sum()
    }

    check(part1("d01/Day01_test") == 24000)
    check(part2("d01/Day01_test") == 45000)

    println(part1("d01/Day01"))
    println(part2("d01/Day01"))
}
