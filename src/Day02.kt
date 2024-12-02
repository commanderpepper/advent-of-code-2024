fun main(){
    fun part1(input:List<String>):Int {
        val reports = getReports(input)
        val sf = reports.map { isReportSafe(report = it, operation = {a, b -> b - a}) || isReportSafe(report = it, operation = {a, b -> a - b}) }
        return sf.count { it }
    }

    fun part2(input:List<String>):Int {
        return 0
    }

    val input = readInput("Day02")
    println(part1(input))
}

fun getReports(input:List<String>): List<List<Int>>{
    return input.map {
        it.split(" ").map { it.toInt() }
    }
}

fun isReportSafe(report: List<Int>, minAmount : Int = 1, maxAmount : Int = 3, operation : (Int, Int) -> (Int)): Boolean {
    val zipWithNext = report.zipWithNext { a, b -> operation(a, b) }
    return zipWithNext.all { it in minAmount ..  maxAmount }
}