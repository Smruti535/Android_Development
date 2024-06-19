fun main() {
    println(sumOrNull(3, 5))    
    println(sumOrNull(7, null)) 
    println(sumOrNull(null, 4)) 
    println(sumOrNull(null, null)) 
}

fun sumOrNull(a: Int?, b: Int?): Int {
    var a1 = a ?: -1 
    var b1 = b ?: -1
    if(a1 == -1 || b1 == -1){
        return -1
    }
    else{
        return a1 + b1
    }
}