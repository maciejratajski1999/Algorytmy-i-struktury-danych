fun BinomialCoefficient(n: Int, k: Int): Int =
    if (k == n || k == 0) {
        1
    } else {
        BinomialCoefficient(n - 1, k - 1) + BinomialCoefficient(n - 1, k)
    }


fun ExponantiationBySquaring(x: Double, n: Int): Double =
    if (n == 0) {
        1.0
    }
    else if (n % 2 == 0) {
        ExponantiationBySquaring(x * x, n / 2)
    } else {
        x * ExponantiationBySquaring(x * x, (n - 1) / 2)
    }

fun MaximumProbability(n: Int, k: Int, p: Double): Double {
    var negativeP: Double = (1-p)
    val pPowers = mutableListOf<Double>(1.0, p)
    val negativePPowers = mutableListOf<Double>(ExponantiationBySquaring(negativeP,(n-k)))
    for (i in 1..(k+1)){
        if (i > 1){
            pPowers.add(pPowers.last()*p)
        }
        negativePPowers.add(0,negativePPowers.first()*negativeP)
    }
    return pPowers.foldIndexed(0.0){index, acc, d -> acc + BinomialCoefficient(n,index)*d*negativePPowers[index] }
}


fun main() {
    println(MaximumProbability(2,1,0.5))
}
