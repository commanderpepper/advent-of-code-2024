
fun main(){
    fun part1(input:List<String>):Int{
        val horizontalRightCount = checkNextFourLetters(wordSearch = input, xMovement = { x -> x }, yMovement = { y -> y + 1 })
        val horizontalLeftCount = checkNextFourLetters(wordSearch = input, xMovement = { x -> x }, yMovement = { y -> y - 1 })
        val verticalUpCount = checkNextFourLetters(wordSearch = input, xMovement = { x -> x - 1 }, yMovement = { y -> y })
        val verticalDownCount = checkNextFourLetters(wordSearch = input, xMovement = { x -> x + 1 }, yMovement = { y -> y })

        val diagonalUpRight = checkNextFourLetters(wordSearch = input, xMovement = { x -> x - 1 }, yMovement = { y -> y + 1})
        val diagonalDownRight = checkNextFourLetters(wordSearch = input, xMovement = { x -> x + 1 }, yMovement = { y -> y + 1})
        val diagonalUpLeft = checkNextFourLetters(wordSearch = input, xMovement = { x -> x - 1 }, yMovement = { y -> y - 1})
        val diagonalDownLeft = checkNextFourLetters(wordSearch = input, xMovement = { x -> x + 1 }, yMovement = { y -> y - 1})

        return horizontalRightCount + horizontalLeftCount + verticalUpCount + verticalDownCount + diagonalUpRight + diagonalDownLeft + diagonalUpLeft + diagonalDownRight
    }

    fun part2(input:List<String>):Int{
        return 0
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun checkNextFourLetters(
    wordSearch: List<String>,
    condition: (String) -> Boolean = { it == "XMAS" },
    xMovement: (Int) -> Int,
    yMovement: (Int) -> Int): Int {
    var count = 0

    wordSearch.forEachIndexed { row, line ->
        line.forEachIndexed { col, character ->
            val word = mutableListOf<Char?>()

            var x = row
            var y = col

            for(i in 0 until 4){
                val letter = wordSearch.getOrNull(x)?.getOrNull(y)
                word.add(letter)
                x = xMovement(x)
                y = yMovement(y)
            }
            if(word.all { it != null } && condition(word.joinToString(""))) { count ++ }
        }
    }

    return count
}
