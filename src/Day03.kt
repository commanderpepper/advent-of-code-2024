fun main(){
    fun part1(input:List<String>):Int{
        return input.map{ line -> getPairsForMult(line).map { it.first * it.second }.sum() }.sum()
    }

    fun part2(input:String):Int{
        return 0
    }

    val input = readInput("Day03")
    println(part1(input))
}

fun getPairsForMult(input: String): List<Pair<Int,Int>>{
    val pairs = mutableListOf<Pair<Int,Int>>()
    println(input)
    val mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    val numbersRegex = "\\d{1,3},\\d{1,3}".toRegex()
    val instructions = mulRegex.findAll(input).map { matchResult ->
        matchResult.value
    }
    val numbers = instructions.map { instruction ->
        numbersRegex.find(instruction)?.value?.split(",")?.map { it.toInt() } ?: emptyList()
    }

    numbers.filter { it.isNotEmpty() }.forEach { list -> pairs.add(Pair(list[0],list[1])) }

    println(pairs)

    return pairs
}