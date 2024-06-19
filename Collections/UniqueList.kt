class UniqueList<T> {
    private val elements = mutableListOf<T>()

    fun add(element: T): Boolean {
        if (!elements.contains(element)) {
            elements.add(element)
            return true
        }
        return false
    }

    fun remove(element: T): Boolean {
        return elements.remove(element)
    }

    fun contains(element: T): Boolean {
        return elements.contains(element)
    }

    fun size(): Int {
        return elements.size
    }

    fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun toString(): String {
        return elements.toString()
    }
}

fun main() {
    val uniqueList = UniqueList<Int>()

    println("Adding 1: ${uniqueList.add(1)}") // true
    println("Adding 2: ${uniqueList.add(2)}") // true
    println("Adding 3: ${uniqueList.add(3)}") // true
    println("Adding 2: ${uniqueList.add(2)}") // false (already exists)

    println("Elements in the UniqueList: $uniqueList")
	println("Removing 2: ${uniqueList.remove(2)}")

    println("Elements in the UniqueList after removal: $uniqueList")

    println("Contains 1: ${uniqueList.contains(1)}")
    println("Contains 2: ${uniqueList.contains(2)}")
}
