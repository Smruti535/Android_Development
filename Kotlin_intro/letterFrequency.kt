fun main() {
    val input = "Hello World"
    val frequency = IntArray(26) 
    for (char in input) {
        if (char.isLetter()) {
            val index = char.lowercaseChar() - 'a' 
            frequency[index]++
        }
    }

    println("Letter frequencies:")
    for (i in frequency.indices) {
        if (frequency[i] > 0) {
            val char = 'a' + i
            println("$char: ${frequency[i]}")
        }
    }
}