fun main(){
    fun part1(input:List<String>):Int{
        return input.map{ line -> getPairsForMult(line).map { it.first * it.second }.sum() }.sum()
    }

    fun part2(input:List<String>):Int{
        val valids = input.map { getValidString(it) }
        return valids.map{ line -> getPairsForMult(line).map { it.first * it.second }.sum() }.sum()
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun getValidString(line: String): String {
    val regex = """(^|do\(\)).*?($|don't\(\))""".toRegex()
    val joinToString = regex.findAll(line).map { it.value }.joinToString(separator = "")
    println(joinToString)
    return joinToString
}

fun getPairsForMult(input: String): List<Pair<Int,Int>>{
    val pairs = mutableListOf<Pair<Int,Int>>()
    val mulRegex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    val numbersRegex = "\\d{1,3},\\d{1,3}".toRegex()

    val instructions = mulRegex.findAll(input).map { matchResult ->
        matchResult.value
    }
    val numbers = instructions.map { instruction ->
        numbersRegex.find(instruction)?.value?.split(",")?.map { it.toInt() } ?: emptyList()
    }

    numbers.filter { it.isNotEmpty() }.forEach { list -> pairs.add(Pair(list[0],list[1])) }
    return pairs
}