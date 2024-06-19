fun main() {
    var list1=listOf(1,2,3,2)
    var list2=listOf(3, 6, 9,7)
    var list3=listOf(2,7,9,8)
    var listOfLists=listOf(list1,list2,list3)
    val flattenedAndUnique = listOfLists
        .flatMap { it }    
        .toSet()           
        .toList()           

    println("Original list of lists: $listOfLists")
    println("Flattened and unique list: $flattenedAndUnique")
    
}    

