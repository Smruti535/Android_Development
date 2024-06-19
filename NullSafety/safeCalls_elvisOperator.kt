fun main() {
    var str1: String? = null
    var str2: String? = "Silicon"
    val length1 = stringLength(str1)
    val length2 = stringLength(str2)
    println("Length of $str1: $length1\nLength of $str2: $length2")
}
 fun stringLength( str: String?): Int {
     return str?.length?:-1
 }