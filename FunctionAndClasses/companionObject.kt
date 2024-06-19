
class User(val name: String, val age: Int) {
    companion object {
        fun createUserWithDefaultAge(name: String): User {
            return User(name, 25)
        }
        
        fun createUser(name: String = "Martin", age: Int =25): User {
            return User(name, age)
        }
       
        
    }
}

fun main() {
    val user1 = User.createUserWithDefaultAge("Jenny")
    println("User 1: Name=${user1.name}, Age=${user1.age}")
    val user2 = User.createUser("Mark", 25)
    println("User 2: Name=${user2.name}, Age=${user2.age}")
    val user3 = User.createUser()
    println("User 3: Name=${user3.name}, Age=${user3.age}")
}