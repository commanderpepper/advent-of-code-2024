fun main(){
    fun part1(input:List<String>):Int{
        val pageOrderingRules = getPageOrderingRules(input)
        val updateMap = getUpdateMap(pageOrderingRules)
        val updateList = getUpdate(input)

        println(updateMap)
        println(pageOrderingRules)
        println(updateList)

        val validLists = updateList.filter { isUpdateValid(it, updateMap) }
        println(validLists)
        val middleElements = validLists.map { getMiddleElement(it) }
        println(middleElements)

        return middleElements.sum()
    }

    fun part2(input:List<String>):Int{
        return 0
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

data class Page(val page: Int, val before: List<Int>, val after: List<Int>)

fun getUpdateMap(pageOrderingRules: List<List<Int>>): Map<Int, Page> {
    val mutableMap = mutableMapOf<Int, Page>()

    pageOrderingRules.forEach {
        val before = it[0]
        val after = it[1]
        mutableMap.getOrPut(before) {Page(page = before, before = emptyList(), after = emptyList())}
        mutableMap.getOrPut(after) {Page(page = after, before = emptyList(), after = emptyList())}
    }

    pageOrderingRules.forEach {
        val before = it[0]
        val after = it[1]
        val beforePage = mutableMap[before]
        beforePage?.let { page ->
            val newAfterList = page.after + listOf(after)
            mutableMap[before] = page.copy(after = newAfterList)
        }
    }

    pageOrderingRules.forEach {
        val before = it[0]
        val after = it[1]
        val afterPage = mutableMap[after]
        afterPage?.let { page ->
            val newBeforeList = page.before + listOf(before)
            mutableMap[after] = page.copy(before = newBeforeList)
        }
    }

    return mutableMap
}

fun getPageOrderingRules(input:List<String>): List<List<Int>> {
    val rulesOnly = input.filter { it.contains("|") }
    return rulesOnly.map { it.split("|").map { it.toInt() } }
}

fun getUpdate(input:List<String>): List<List<Int>> {
    val updatesOnly = input.filter { it.contains(",") }
    return updatesOnly.map { it.split(",").map { it.toInt() } }
}

fun isUpdateValid(pages: List<Int>, pageMap: Map<Int, Page>): Boolean {
    return pages.all { page ->
        val fullPageInfo = pageMap[page]
        val index = pages.indexOf(page)
        val beforeList = pages.subList(0, index)
        val afterList = pages.subList(index + 1, pages.size)

        fullPageInfo?.before?.containsAll(beforeList) == true && fullPageInfo.after.containsAll(afterList)
    }
}

fun getMiddleElement(pages: List<Int>): Int {
    val end = pages.size - 1
    val middle = end / 2
    return pages[middle]
}