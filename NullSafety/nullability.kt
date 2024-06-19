fun main() {
    val nullableIntegers: List<Int?> = listOf(1,2,null,3,null,7,null)
    val notNull = filterNotNullInt(nullableIntegers)
    println("Non-null integers are: $notNull")
}

fun filterNotNullInt(list: List<Int?>):List<Int>{
    return list.filterNotNull()
    
}