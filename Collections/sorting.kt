fun main() {
     var persons=listOf(
         Person("Rahul", 23),
         Person("Sameer", 21),
         Person("Rakesh", 25),
         Person("Bob", 30),
         Person("Eve", 20),
         Person("David", 25),
         Person("Charlie", 20))
     val sortedPersons = persons.sortedWith(compareBy({ it.age }, { it.name }))

    println("Sorted Persons:")
    sortedPersons.forEach { println("${it.name} (${it.age})") }
}
data class Person (val name:String , val age:Int) 