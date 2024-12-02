import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (leftList, rightList) = getLeftAndRight(input)
        val sortedLeft = leftList.sorted()
        val sortedRight = rightList.sorted()

        val totalDistance = sortedLeft.zip(sortedRight){ left, right -> abs(left - right) }.sum()

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (leftList, rightList) = getLeftAndRight(input)
        val leftListCount = leftList.map { l ->
            rightList.count { it == l }
        }
        val similarityScore = leftList.zip(leftListCount) { left, count -> left * count }.sum()

        return similarityScore
    }

    println(part1(readInput("Day01")))
    println(part2(readInput("Day01")))
}

fun getLeftAndRight(input: List<String>): Pair<List<Int>, List<Int>> {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    input.forEach {
        val pair = it.split(" ").filter{ it.isNotEmpty() }
        leftList.add((pair.first().toInt()))
        rightList.add((pair.last().toInt()))
    }
    return leftList.toList() to rightList.toList()
}