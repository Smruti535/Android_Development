object DatabaseConnection {
    fun connect() {
        println("Connected to database")
    }

    fun disconnect() {
        println("Disconnected from database")
    }
}

fun main() {
    DatabaseConnection.connect()
    DatabaseConnection.disconnect()
}