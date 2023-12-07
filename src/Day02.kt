fun String.colorCount(): Map<String, Int> {
    val colors = hashMapOf<String, Int>().withDefault { 0 }
    this.split(",").map { it.trim() }.forEach {
        val (count, color) = it.split(" ")
        colors[color] = count.toInt()
    }
    return colors
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.mapIndexed { i, s ->
            val game = s.split(":")[1].split(";")
            if (game.all {
                    it.colorCount().run {
                        getValue("red") <= 12 &&
                                getValue("green") <= 13 &&
                                getValue("blue") <= 14
                    }
                }) i + 1
            else 0
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { s ->
            val game = s.split(":")[1].split(";")
            game.map { it.colorCount() }.run {
                maxOf { it.getValue("red") } *
                        maxOf { it.getValue("green") } *
                        maxOf { it.getValue("blue") }
            }
        }
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
