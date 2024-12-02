fun main(){
    fun part1(input:List<String>):Int {
        val reports = getReports(input)
        val sf = reports.map { isReportSafe(report = it, operation = {a, b -> b - a}) || isReportSafe(report = it, operation = {a, b -> a - b}) }
        return sf.count { it }
    }

    fun part2(input:List<String>):Int {
        val reports = getReports(input)
        val sf = reports.map {
            isReportSafe(report = it, operation = { a, b -> b - a })
                    || isReportSafeWithDampener(report = it, operation = { a, b -> b - a })
                    || isReportSafe(report = it, operation = { a, b -> a - b })
                    || isReportSafeWithDampener(report = it, operation = { a, b -> a - b })
        }
        return sf.count { it }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
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

fun isReportSafeWithDampener(report: List<Int>, minAmount : Int = 1, maxAmount : Int = 3, operation : (Int, Int) -> (Int)): Boolean {
    val reportsWithOneItemDropped = mutableListOf<List<Int>>()
    for(i in 0 until report.size){
        val ml = report.toMutableList()
        val r = ml.removeAt(i)
        reportsWithOneItemDropped.add(ml.toList())
    }
    println(reportsWithOneItemDropped)
    val areAnySafe = reportsWithOneItemDropped.any { isReportSafe(report = it, operation = operation) }
    return areAnySafe
}