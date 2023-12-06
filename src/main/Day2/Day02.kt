package Day2

enum class Color {
    Red,
    Green,
    Blue,
}

data class Game(
    val id: Int,
    val results: List<Map<Color, Int>>,
)

data class GameMinimums(
    val id: Int,
    var red: Int = 0,
    var green: Int = 0,
    var blue: Int = 0,
)

class Day02 {
    fun parseGameResult(gameResultsString: String): Map<Color, Int> = gameResultsString.split(",")
        .map(String::trim)
        .fold(mutableMapOf(Color.Red to 0, Color.Blue to 0, Color.Green to 0)) { acc, result ->
            val (resultNumber, color) = result.split(" ")
            when (color) {
                "red" -> acc[Color.Red] = acc[Color.Red]!! + resultNumber.toInt()
                "green" -> acc[Color.Green] = acc[Color.Green]!! + resultNumber.toInt()
                "blue" -> acc[Color.Blue] = acc[Color.Blue]!! + resultNumber.toInt()
            }

            acc
        }

    fun parseInput(line: String): Game {
        val gameIdRegex = Regex("Game (\\d+)")

        val (gameId, games) = line.split(":", ignoreCase = true)
        val gameIdMatches = gameIdRegex.findAll(gameId)

        // 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        // Split on ";" so each one will be ["3 blue, 4 red", "1 red, 2 green", "2 green"]
        // Each one needs to be mapped into a game result

        val gameResults = games.split(";").map { colorCounts ->
            parseGameResult(colorCounts)
        }

        return Game(
            id = gameIdMatches.first().groups[1]!!.value.toInt(),
            results = gameResults,
        )
    }

    fun part1(input: List<String>): Int {
        val expected = mapOf(
            Color.Red to 12,
            Color.Green to 13,
            Color.Blue to 14,
        )

        val games = input.map(::parseInput)
        return games.filter { game ->
            expected.keys.all { color ->
                game.results.all {
                    it[color]!! <= expected[color]!!
                }
            }
        }.sumOf { it.id }
    }

    fun determineMinimums(game: Game): GameMinimums = game.results.fold(GameMinimums(id = game.id)) { acc, result ->
            result.entries.forEach { (color, value) ->
                when(color) {
                    Color.Red -> acc.red = maxOf(value, acc.red)
                    Color.Blue -> acc.blue = maxOf(value, acc.blue)
                    Color.Green -> acc.green = maxOf(value, acc.green)
                }
            }

            acc
        }

    fun calculatePower(gameMinimums: GameMinimums): Int = gameMinimums.red * gameMinimums.blue * gameMinimums.green

    fun part2(input: List<String>): Int {
        return input.map(::parseInput)
            .map(::determineMinimums)
            .map(::calculatePower)
            .sum()
    }
}
