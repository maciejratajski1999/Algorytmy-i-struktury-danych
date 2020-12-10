import Fraction.Fraction
import kotlin.math.pow

var cnt = 0
var cnt2 = 0

fun binomialTheorem(n: Int, k: Int) = multiplyAll((0..(k-1))
    .map{l -> Fraction((n-l),(k-l)) }).getNum()
fun multiplyAll(numbers: List<Fraction>) = numbers.fold(Fraction(1,1), {acc, elem -> (acc * elem).also { cnt++; cnt2++ } })

fun probability(n: Int, k:Int, p: Double) = (0..k).map{i -> binomialTheorem(n, i)*(p.pow(i).also { cnt = cnt + i })*((1-p)
    .pow(n-i).also { cnt = cnt+n-i })}.also { cnt = cnt + 2*(k+1) }
    .sum()

fun probability2(n: Int, k: Int, p: Double) = (0..k).fold(PartialSum(listOf(),1.0), { accSum, i ->
    PartialSum(accSum.acc +
        (binomialTheorem(n,i)*(accSum.p)),accSum.p*p).also{cnt2 = cnt2 + 2}})

fun probability3(n: Int, k: Int, p: Double) = probability2(n, k, p).acc.foldRight(PartialSum2(0.0, (1-p).pow(n-k)),
    {elem, accSum ->
    PartialSum2(accSum.acc + (elem*accSum.q),accSum.q*(1-p)).also { cnt2 = cnt2 + 2 }})

fun main() {
    println(probability(100,99, 0.5))
    println(cnt)

    println(probability3(100,99, 0.5))
    println(cnt2)
}

data class PartialSum(val acc: List<Double>, val p: Double)

data class PartialSum2(val acc: Double, val q: Double)