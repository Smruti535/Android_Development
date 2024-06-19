fun main() {
    val person1 = Person(Address("New York"))
    val person2 = Person(Address(null))
    val person3 = Person(null)
    val person4 = Person(Address("San Francisco"))

    println(getCityName(person1)) 
    println(getCityName(person2)) 
    println(getCityName(person3)) 
    println(getCityName(person4)) 
}

data class Person(val address: Address?)
data class Address(val city: String?)

fun getCityName(person: Person): String {
    return person.address?.city ?: "Unknown City"
}

