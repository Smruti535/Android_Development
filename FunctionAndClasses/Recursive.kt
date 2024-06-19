fun factorial(n: Int): Int {
    if (n <= 1){
    	return n    
    }
    else{
        return(n * factorial(n - 1))
    }
}

fun main() {
    println("Factorial of 5: ${factorial(5)}")
}