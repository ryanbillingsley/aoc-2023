package Day1

import println
import readInput

fun main() {
    fun calculateLineTotal(line: String): Int {
        val digitRegex = Regex("(\\d)")
        val digits = digitRegex.findAll(line)
            .map { matchResult -> matchResult.value }
            .toList()

        val firstLast = digits.first() + digits.last()

        return firstLast.toInt()
    }

    fun part1(input: List<String>): Int {
        return input.fold(0) { acc, line ->
            acc + calculateLineTotal(line)
        }
    }

    val numberWords = mapOf(
        "eight" to "8",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "nine" to "9",
        "one" to "1",
    )

    fun calculateLineTotalWithWords(line: String): Int {
        val digitRegex = Regex("([1-9])")

        val possibleNumbers = listOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )
        val numberIndexMap = possibleNumbers.fold(mutableMapOf<Int, String>()) { acc, number ->
            val numberRegex = Regex(number)
            numberRegex.findAll(line)
                .forEach { matchResult ->
                    acc[matchResult.range.first] = number
                }
            acc
        }.mapValues {
            if (digitRegex.matches(it.value)) {
                it.value
            } else {
                numberWords[it.value]
            }
        }
        .toSortedMap()

        val firstLast = numberIndexMap.values.first() + numberIndexMap.values.last()
        return firstLast.toInt()
    }

    fun part2(input: List<String>): Int {
        return input.fold(0) { acc, line ->
            acc + calculateLineTotalWithWords(line)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day1/Day01_test")
    check(calculateLineTotal("1abc2") == 12)
    check(calculateLineTotal("a1b2c3d4e5f") == 15)
    check(calculateLineTotal("treb7uchet") == 77)

    check(part1(testInput) == 142)


    val testInputPart2 = readInput("Day1/Day01_Part2_test")
    check(calculateLineTotalWithWords("crvhlfone7xsqhkshpsix2nine73oneighttq") == 18)
    check(calculateLineTotalWithWords("two1nine") == 29)
    check(calculateLineTotalWithWords("eightwothree") == 83)
    check(calculateLineTotalWithWords("abcone2threexyz") == 13)
    check(calculateLineTotalWithWords("xtwone3four") == 24)
    check(calculateLineTotalWithWords("4nineeightseven2") == 42)
    check(calculateLineTotalWithWords("zoneight234") == 14)
    check(calculateLineTotalWithWords("7pqrstsixteen") == 76)
    check(part2(testInputPart2) == 281)

    val input = readInput("Day1/Day01")
    println("Part1")
    part1(input).println()

    println("Part2")
    part2(input).println()
}
