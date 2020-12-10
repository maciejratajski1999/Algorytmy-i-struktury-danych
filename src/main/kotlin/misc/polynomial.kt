//fun polynom(x: Double, a: List<Double>) = (a[2]*x + a[1])*x + a[0]
fun polynom(x: Double, a: List<Double>) = a.foldRight(0.0){d, acc -> acc*x + d }

fun main() {
    println(polynom(1.0, listOf(5.0, 0.0, 1.0, -3.0)))
}
