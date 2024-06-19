fun main() {
    val number1 = 56
    val number2 = 98

    var num1 = number1
    var num2 = number2

    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }

    println("The GCD of $number1 and $number2 is: $num1")
}