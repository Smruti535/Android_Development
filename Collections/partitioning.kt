fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val (evenNumbers, oddNumbers) = partitionNumbers(numbers)

    println("Original list: $numbers")
    println("Even numbers: $evenNumbers")
    println("Odd numbers: $oddNumbers")
}

fun partitionNumbers(numbers: List<Int>): Pair<List<Int>, List<Int>> {
    return numbers.partition { it % 2 == 0 }
}
