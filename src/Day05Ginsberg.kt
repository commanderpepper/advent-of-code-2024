/**
 * Solution from Todd Ginsberg
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2024/day5/
 */
fun main() {
    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<String>): Int {
    val rules = getRules(input)
    val updates = getUpdates(input)
    val comparator = getComparator(rules)

    return updates.map { formatCorrectly(it, comparator) }
        .filter { it.first == it.second }
        .sumOf { it.second.midpoint().toInt() }
}

fun part2(input: List<String>): Int {
    val rules = getRules(input)
    val updates = getUpdates(input)
    val comparator = getComparator(rules)

    return updates
        .map { formatCorrectly(it, comparator) }
        .filterNot { it.first == it.second }
        .sumOf { it.second.midpoint().toInt() }
}

private fun getComparator(rules: Set<String>): Comparator<String> {
    return Comparator { a, b ->
        when {
            "$a|$b" in rules -> -1
            "$b|$a" in rules -> 1
            else -> 0
        }
    }
}

private fun getRules(input: List<String>): Set<String> {
    return input
        .takeWhile { it.isNotEmpty() }
        .toSet()
}

private fun getUpdates(input: List<String>): List<List<String>> {
    return input.dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { row -> row.split(",") }
}

private fun formatCorrectly(update: List<String>, comparator: Comparator<String>): Pair<List<String>, List<String>> =
    update to update.sortedWith(comparator)