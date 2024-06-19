fun String.isPalindrome(): Boolean {
    
    var i = 0
    var j = this.length - 1

    while (i < j) {
        if (this[i] != this[j]) {
            return false
        }
        i++
        j--
    }
    return true
}

fun main() {
    val str = "level"
		if(str.isPalindrome()){
            println("$str is a palindrome")
        }
        else{
            println("$str is not a palindrome")
        }
}