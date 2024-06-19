fun main() {
    val user1 = User(name = "Akash", email = "akash@example.com")
    val user2 = User(name = "Rahul", email = null)
    val user3 = User(name = null, email = "rahul@example.com")
    val user4 = User(name = null, email = null)

    printUserDetails(user1) 
    printUserDetails(user2) 
    printUserDetails(user3)
    printUserDetails(user4) 
}

data class User(val name: String?, val email: String?)

fun printUserDetails(user: User) {
    if (user.name == null || user.email == null) {
        println("Incomplete User")
    } else {
        println(user)
    }
}
