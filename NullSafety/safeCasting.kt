fun main() {
    val mixedList: List<Any> = listOf("apple", 1, "banana", 2, "orange", 3, "kiwi")

    val filteredStrings = filterStrings(mixedList)

    println("Original list: $mixedList")
    println("Filtered strings: $filteredStrings")
}

fun filterStrings(list: List<Any>): List<String> {
    val result = mutableListOf<String>()

    for (element in list) {
        if (element is String) {
            result.add(element)
        }
    }

    return result
}
