fun main() {
    val str1 : String?= "hello"
    val str2 : String? =  null
    upperCase(str1)
    upperCase(str2)
}
fun upperCase(str: String?){
    str?.let {
        println(it.toUpperCase())
    } ?: run {
        println("String is null")
    }
}