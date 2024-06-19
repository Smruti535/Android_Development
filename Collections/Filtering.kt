fun main() {
    var numbers=listOf(1,2,3,4,5,6,7,8,9,10)
    
    var doubledEvenNumbers=numbers
        .filter{ it%2 == 0 }
        .map{ it*2 }
    println("Original list: $numbers")
    println("Resulting list: $doubledEvenNumbers")
}