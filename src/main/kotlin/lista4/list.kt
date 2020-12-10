package lista4


sealed class MList< out T> {
    class NIL<T> : MList<T>() {
        override fun toString(): String = "[]"
        override fun tail(): MList<T> = this
        override fun head(): T? = null
    }

    class Node<T>(private val t: T, private val tail: MList<T>) : MList<T>() {
        override fun toString(): String = t.toString() + ", " + tail.toString()
        override fun tail(): MList<T> = tail
        override fun head(): T = t
    }

    abstract fun tail(): MList<T>
    abstract fun head(): T?



    companion object {
        val emptyObj = MList.NIL<Nothing>()
        @Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")
//        inline fun <T> empty():MList<T> = emptyObj //contrary to expectation this is a little slower!
        inline fun <T> empty():MList.NIL<T> = NIL()
    }
}
@Suppress("NOTHING_TO_INLINE")
inline fun <T> MList<T>.prepend(t:T) = MList.Node(t, this)


fun <T> MList.Node<T>.last():T = this.tail().let {tail ->
    when (tail) {
        is MList.Node -> tail.last()
        is MList.NIL -> this.head()
    }
}
fun <T> MList.NIL<T>.last():T? = null



fun <T> MList<T>.reverse() = when (this) {
    is MList.NIL -> this
    else -> reverseHelper(this, MList.empty<T>())
}

tailrec fun <T> reverseHelper(list:MList<T>, acc:MList<T>):MList<T> = when (list) {
    is MList.Node -> reverseHelper(list.tail(), acc.prepend(list.head()))
    else -> acc
}

private tailrec fun <T> appendHelper(list:MList<T>, acc:MList<T>, last: T):MList<T> = when (list) {
    is MList.NIL -> acc.prepend(last)
    is MList.Node -> appendHelper(list.tail(), acc.prepend(list.head()), last)
}

fun <T> MList<T>.append( last: T): MList<T> = appendHelper(this, MList.empty<T>(), last).reverse()

fun main() {
    val x = MList.NIL<Int>()
    println(x)

    val y = MList.Node(7,x)
    println(y)

    val z = MList.Node(5, y)
    println(z)

    val z2 = z.append(10).append(-2)
    println(z2)

    val z3 = z2.append(-17)
    println(z3)
}
