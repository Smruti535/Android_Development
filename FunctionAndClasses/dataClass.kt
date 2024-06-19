data class Person(val name: String, val age: Int, val address: String)
fun main() {
    val person1 = Person("Smruti Smaranika", 21, "Bhubaneswar, Odisha")
    
    val person2 = person1.copy(name="khushi kumari", age = 22, )
    
    println("Original Person: $person1")
    println("New Person: $person2")
}
