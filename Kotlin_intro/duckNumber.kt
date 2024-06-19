fun main() {
    val number = 1023
    
    var isDuckNumber = false
    var tempNumber = number

    if (tempNumber % 10 == 0) {
        tempNumber /= 10
    }

    while (tempNumber > 0) {
        if (tempNumber % 10 == 0) {
            isDuckNumber = true
            break
        }
        tempNumber /= 10
    }

    if (isDuckNumber) {
        println("$number is a Duck Number")
    } else {
        println("$number is not a Duck Number")
    }
}