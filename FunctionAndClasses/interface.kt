interface Drawable {
    fun draw()
}

class Square : Drawable {
    override fun draw() {
        println("Drawing Square")
    }
    
    fun resize() {
        println("Resizing Square")
    }
}
class Triangle : Drawable {
    override fun draw() {
        println("Drawing Triangle")
    }
    
    fun resize() {
        println("Resizing Triangle")
    }
}

fun main() {
    val square = Square()
    val triangle = Triangle()
    println("--- Square ---")
    square.draw()
    square.resize()
    
    println("--- Triangle ---")
    triangle.draw()
    triangle.resize()
}