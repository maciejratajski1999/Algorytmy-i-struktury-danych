package fibonacci

val base_numbers= mutableListOf(0,1)

fun fibonacci(n: Int): Int = if (n <= 1) n else fibonacci(n-1)+fibonacci(n-2)
tailrec fun fib1(n: Int, sum: Int, sum1: Int):Int = if (n <= 1) sum else fib1(n-1, sum + sum1,  sum)

fun fib2(n:Int) = fib1(n, 1, 0)

fun main() {
    println(fib2(160000))
}