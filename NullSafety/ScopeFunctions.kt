fun main() {
    val list1: List<Int>? = listOf(5, 3, 8, 1)
    val list2: List<Int>? = null

    printSortedList(list1)
    printSortedList(list2) 
}

fun printSortedList(list: List<Int>?) {
    list?.run {
        val sortedList = this.sorted()
        println(sortedList)
    } ?: println("The list is null")
}

