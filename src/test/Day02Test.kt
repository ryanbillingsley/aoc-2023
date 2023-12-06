import Day2.Color
import Day2.Day02
import Day2.Game
import Day2.GameMinimums
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test {
    @Test
    fun shouldParseGameResult() {
        val day02 = Day02()

        day02.parseGameResult("3 blue, 4 red") shouldBe mapOf(Color.Blue to 3, Color.Red to 4, Color.Green to 0)
    }

    @Test
    fun shouldParseTestGameInput() {
        val day02 = Day02()
        val result = day02.parseInput("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")

        result shouldBe Game(
            id = 1, results = listOf(
                mapOf(
                    Color.Blue to 3,
                    Color.Red to 4,
                    Color.Green to 0,
                ),
                mapOf(
                    Color.Blue to 6,
                    Color.Red to 1,
                    Color.Green to 2,
                ),
                mapOf(
                    Color.Blue to 0,
                    Color.Red to 0,
                    Color.Green to 2,
                ),
            )
        )
    }

    @Test
    fun shouldParseLongerInput() {

        val day02 = Day02()
        val result =
            day02.parseInput("Game 93: 12 green, 2 blue, 2 red; 8 red, 16 green, 8 blue; 15 red, 4 blue, 7 green; 1 red, 4 blue, 15 green; 13 green, 5 red, 4 blue; 5 green, 8 blue, 12 red")

        result shouldBe Game(
            id = 93, results = listOf(
                mapOf(
                    Color.Blue to 2,
                    Color.Red to 2,
                    Color.Green to 12,
                ),
                mapOf(
                    Color.Blue to 8,
                    Color.Red to 8,
                    Color.Green to 16,
                ),
                mapOf(
                    Color.Blue to 4,
                    Color.Red to 15,
                    Color.Green to 7,
                ),
                mapOf(
                    Color.Red to 1,
                    Color.Blue to 4,
                    Color.Green to 15,
                ),
                mapOf(
                    Color.Blue to 4,
                    Color.Red to 5,
                    Color.Green to 13,
                ),
                mapOf(
                    Color.Blue to 8,
                    Color.Red to 12,
                    Color.Green to 5,
                ),
            )
        )
    }

    @Test
    fun shouldCorrectlySolveTestInput() {
        val testInputPart1 = readInput("Day2/Day02_Part1_test")
        val day02 = Day02()

        day02.part1(testInputPart1) shouldBe 8
    }

    @Test
    fun runsPart1() {
        val day02 = Day02()
        val input = readInput("Day2/Day02")

        day02.part1(input).println()
    }

    @Test
    fun shouldFindTheMinimumOfEachColorNeeded() {
        val day02 = Day02()

        val input = Game(
            id = 1,
            results = listOf(
                mapOf(
                    Color.Blue to 3,
                    Color.Red to 4,
                    Color.Green to 0,
                ),
                mapOf(
                    Color.Blue to 6,
                    Color.Red to 1,
                    Color.Green to 2,
                ),
                mapOf(
                    Color.Blue to 0,
                    Color.Red to 0,
                    Color.Green to 2,
                ),
            )
        )
        val expected = GameMinimums(
            id = 1,
            red = 4,
            green = 2,
            blue = 6
        )

        day02.determineMinimums(input) shouldBe expected
    }

    @Test
    fun shouldDeterminePowerOfAGameMinimum() {

        val input = GameMinimums(
            id = 1,
            red = 4,
            green = 2,
            blue = 6
        )

        val day02 = Day02()
        day02.calculatePower(input) shouldBe 48
    }

    @Test
    fun shouldParseTestInput() {
        val testInputPart2 = readInput("Day2/Day02_Part2_test")
        val day02 = Day02()

        day02.part2(testInputPart2) shouldBe 2286
    }

    @Test
    fun runsPart2() {
        val day02 = Day02()

        val input = readInput("Day2/Day02")
        day02.part2(input).println()
    }
}