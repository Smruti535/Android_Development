fun main() {
   
    val rectangle = Rectangle(5.0, 3.0)
    val circle = Circle(4.0)
    println("Area of rectangle : ${rectangle.area()}")
    println("Perimeter of rectangle: ${rectangle.perimeter()}")
    println("Area of circle: ${circle.area()}")
    println("Perimeter of circle: ${circle.perimeter()}")
   
}

abstract class Shape {
    abstract fun area(): Double
   	
}

class Rectangle( val width: Double, val height: Double) : Shape() {
    override fun area(): Double {
        return width * height
    }

   fun perimeter(): Double {
        return 2 * (width + height)
    }
}

class Circle( val radius: Double) : Shape() {
    
    override fun area(): Double {
        return Math.PI * radius * radius
    }
    
   	fun perimeter(): Double {
        return 2 * Math.PI * radius
    }
}
