fun main() {
    val list1 = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(3, 4, 5, 6, 7)

    val intersection = findIntersection(list1, list2)
    val union = findUnion(list1, list2)

    println("List 1: $list1")
    println("List 2: $list2")
    println("Intersection: $intersection")
    println("Union: $union")
}

fun findIntersection(list1: List<Int>, list2: List<Int>): List<Int> {
    val set1 = list1.toSet()
    val set2 = list2.toSet()
    return set1.intersect(set2).sorted()
}

fun findUnion(list1: List<Int>, list2: List<Int>): List<Int> {
    val set1 = list1.toSet()
    val set2 = list2.toSet()
    return (set1 + set2).sorted()
}
