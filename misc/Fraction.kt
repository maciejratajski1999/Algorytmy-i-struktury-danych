package Fraction

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class Fraction(num: Int, dem: Int) {

//    value names and types
    private val numerator: Int
    private val denominator: Int

    init {
//        catching if denominator is 0
        if (dem == 0) throw IllegalArgumentException("denominator cannot be 0")
//        simplify the Fraction using Greatest Common Denominator (gcd)
        val gcd = gcd(num,dem)
//        assigning values
        numerator = num / gcd
        denominator = dem /gcd
    }

//    additional constructor for String arguments
    constructor(numerator: String, denominator: String) : this(numerator.toIntParser(), denominator.toIntParser())


    override fun toString() = "$numerator/$denominator"

    operator fun plus(other: Fraction) =
        Fraction(this.numerator * other.denominator + other.numerator * denominator, denominator * other.denominator)

    operator fun minus(f2: Fraction) =
        Fraction(numerator * f2.denominator - f2.numerator * denominator, denominator * f2.denominator)

    operator fun times(f2: Fraction) = Fraction(numerator * f2.numerator, denominator * f2.denominator)
    fun reciprocal() = Fraction(denominator, numerator)
    operator fun div(f2: Fraction) = times(f2.reciprocal())

    operator fun compareTo(other: Fraction) = (this - other).let { it.numerator * it.denominator }

    fun getNum() = numerator
    fun getDem() = denominator


}

// String method converting String.toInt if possible, or throwing a needed Exception
fun String.toIntParser() = try {
    this.toInt()
} catch (error: NumberFormatException) {
    throw IllegalAccessException("$this is an invalid argument, please give an integer")
}

//Euclidean algorithm for Greatest Common Divisor (gcd)
fun gcd(a: Int, b: Int):Int = if(b==0) a else gcd(b, a % b)