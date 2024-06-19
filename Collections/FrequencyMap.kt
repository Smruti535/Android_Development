fun main() {
    val numbers = listOf(1, 2, 3, 2, 4, 1, 3, 5, 2, 3, 4, 1)

    val frequencyMap = countFrequencies(numbers)

    println("Original list of numbers: $numbers")
    println("Frequency map: $frequencyMap")
    
}

fun countFrequencies(numbers: List<Int>): Map<Int, Int> {
    return numbers.groupingBy { it }.eachCount()
}