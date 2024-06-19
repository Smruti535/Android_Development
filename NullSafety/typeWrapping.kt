class Optional<T>(val value: T?)
fun <T> wrapNullable(value: T?): Optional<T> {
    return Optional(value)
}

fun main() {
    val stringValue: String? = "Hello"
    val intValue: Int? = null

    val wrappedString = wrapNullable(stringValue)
    val wrappedInt = wrapNullable(intValue)

    println("Wrapped String: $wrappedString")
    println("Wrapped Int: $wrappedInt")
   
}