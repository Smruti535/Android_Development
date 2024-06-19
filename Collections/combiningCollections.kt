fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(10, 20, 30)

    val alteredList = alternatedLists(list1, list2)

    println("List 1: $list1")
    println("List 2: $list2")
    println("Alternated List: $alteredList")
}

fun alternatedLists(list1: List<Int>, list2: List<Int>): List<Int> {
    val alteredList = mutableListOf<Int>()
    val size = maxOf(list1.size, list2.size)

    for (i in 0 until size) {
        if (i < list1.size) {
            alteredList.add(list1[i])
        }
        if (i < list2.size) {
            alteredList.add(list2[i])
        }
    }

    return alteredList
}
