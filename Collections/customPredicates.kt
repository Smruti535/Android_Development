fun main() {
    val strings = listOf("apple", "banana", "orange", "grape", "pineapple", "kiwi")
    val filteredStrings1 = filterBycontent(strings , "an") 
    val filteredStrings2 = filterByLength(strings, 5)

    println("Original list of strings: $strings")
    println("Filtered strings (contains 'an'): $filteredStrings1")
    println("Filtered strings (length <= 5): $filteredStrings2")
}
fun filterBycontent(strings: List<String>, substr: String): List<String>{
    return strings.filter{
        it.contains(substr)
        
    }
}
fun filterByLength(strings: List<String>, length: Int): List<String> {
    return strings.filter{ it.length<=5 }

}
