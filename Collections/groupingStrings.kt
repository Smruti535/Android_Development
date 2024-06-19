fun main() {
    
    val strings = listOf("apple", "banana", "avocado", "orange", "pear", "grape", "pineapple", "peach")
    val groupedMap = strings.groupBy({ it.first() }, { it })
    val countMap = groupedMap.mapValues { it.value.size }

    println("Original list: $strings")
	println("Grouped Map:")
    groupedMap.forEach { (key, value) ->
        println("key: $key -> value: $value -> count: ${value.size}")
    }

}  