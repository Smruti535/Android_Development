fun calculateArea(length: Double, breadth: Double): Double {
    return length * breadth
}
fun calculateArea(radius: Double): Double {
    return Math.PI * radius * radius
}

fun main() {
    val rectangleLength = 5.0
    val rectangleBreadth = 3.0
    val circleRadius = 4.0
	
    val rectangleArea = calculateArea(rectangleLength, rectangleBreadth)
    println("Area of Rectangle: $rectangleArea")

    val circleArea = calculateArea(circleRadius)
    println("Area of Circle: $circleArea")
}
