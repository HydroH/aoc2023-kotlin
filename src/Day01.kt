val numMap: Map<String, Int> = hashMapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
).apply {
    for (ch in '0'..'9') {
        this[ch.toString()] = ch.digitToInt()
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val cal1 = it.find { it.isDigit() }?.digitToInt() ?: 0
            val cal2 = it.findLast { it.isDigit() }?.digitToInt() ?: 0
            cal1 * 10 + cal2
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { s -> 
            var cal1 = 0
            var cal2 = 0
            
            run loop@{
                for (i in s.length downTo 1) {
                    numMap.entries.forEach { (k, v) ->
                        if (s.takeLast(i).startsWith(k)) {
                            cal1 = v
                            return@loop
                        }
                    }
                }
            }
            run loop@{
                for (i in s.length downTo 1) {
                    numMap.entries.forEach { (k, v) ->
                        if (s.take(i).endsWith(k)) {
                            cal2 = v
                            return@loop
                        }
                    }
                }
            }
            cal1 * 10 + cal2
        }
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
