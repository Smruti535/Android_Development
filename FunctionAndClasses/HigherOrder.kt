fun operation(a: Int, b: Int, operationFunction: (Int, Int) -> Int): Int {
    return operationFunction(a, b)
}

fun main() {
    val sum = operation(10, 5) { x, y -> x + y }
    val difference = operation(10, 5) { x, y -> x - y }
    val product = operation(10, 5) { x, y -> x * y }
    
    println("Sum: $sum")
    println("Difference: $difference")
    println("Product: $product")
}
