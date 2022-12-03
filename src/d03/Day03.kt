package d03

import readInput

fun main() {
    fun Iterable<Char>.prioritise() =
        sumOf { char -> ((char - 'a').takeIf { it > 0 } ?: ((char - 'A') + 26)) + 1 }.toLong()

    val input = readInput("d03/Day03_test")
    val part1 = input
        .flatMap { arg ->
            val mid = arg.length / 2
            arg.substring(0 until mid).toSet() intersect arg.substring(mid).toSet()
        }.prioritise()

    val part2 = input
        .chunked(3)
        .flatMap { it.reduce { acc, s -> (acc.toSet() intersect s.toSet()).joinToString() }.asIterable() }
        .prioritise()

    println(part1)
    println(part2)
}