package d02

import readInput

enum class Card(val value: Int) {
    Rock(1),
    Paper(2),
    Scissors(3)
}

fun Card.next() = when (this) {
    Card.Rock -> Card.Paper
    Card.Paper -> Card.Scissors
    Card.Scissors -> Card.Rock
}

fun Card.prev() = when (this) {
    Card.Rock -> Card.Scissors
    Card.Paper -> Card.Rock
    Card.Scissors -> Card.Paper
}

enum class Opponent(val card: Card) {
    A(Card.Rock),
    B(Card.Paper),
    C(Card.Scissors);
}

enum class Strategy(val card: Card, val result: Result) {
    X(Card.Rock, Result.Lose),
    Y(Card.Paper, Result.Draw),
    Z(Card.Scissors, Result.Win);
}

enum class Result(val score: Int) {
    Win(6),
    Draw(3),
    Lose(0)
}

fun main() {
    fun part1(opponent: Opponent, strategy: Strategy): Int =
        when (opponent.card) {
            strategy.card -> Result.Draw
            strategy.card.prev() -> Result.Win
            else -> Result.Lose
        }.score + strategy.card.value

    fun part2(opponent: Opponent, strategy: Strategy): Int {
        val card = when (strategy.result) {
            Result.Win -> opponent.card.next()
            Result.Draw -> opponent.card
            Result.Lose -> opponent.card.prev()
        }
        return card.value + strategy.result.score
    }

    val oppMap = Opponent.values().associateBy { it.name }
    val strategyMap = Strategy.values().associateBy { it.name }

    val input = readInput("d02/Day02_test")
        .map { it.split(" ") }

    val result1 = input.sumOf { (a, b) -> part1(oppMap[a]!!, strategyMap[b]!!) }
    val result2 = input.sumOf { (a, b) -> part2(oppMap[a]!!, strategyMap[b]!!) }

    println(result1)
    println(result2)
}