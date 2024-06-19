fun main() {
    val number = 371
    var originalNumber: Int
    var remainder: Int
    var sum = 0

    originalNumber = number

    while (originalNumber != 0) {
        remainder = originalNumber % 10
        sum += remainder*remainder*remainder
        originalNumber /= 10
    }

    if (sum == number)
        println("$number is an Armstrong number.")
    else
        println("$number is not an Armstrong number.")
}