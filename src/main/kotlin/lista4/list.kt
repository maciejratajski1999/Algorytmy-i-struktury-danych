package lista4

sealed class MList<T> {
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
    fun prepend(t:T) = MList.Node(t, this)
    companion object {
        fun <T> empty() = MList.NIL<T>()
    }
}
fun <T> MList.Node<T>.last():T = this.tail().let {tail ->
    when (tail) {
        is MList.Node -> tail.last()
        is MList.NIL -> this.head()
    }
}

fun <T> MList<T>.reverse() = when (this) {
    is MList.NIL -> this
    is MList.Node -> reverseHelper(this, MList.empty())
}

private tailrec fun <T> reverseHelper(list:MList<T>, acc:MList<T>):MList<T> = when (list) {
    is MList.NIL -> acc
    is MList.Node -> reverseHelper(list.tail(), acc.prepend(list.head()))
}

fun <T> append(list: MList<T>, last: T):MList.Node<T> = when(list){
    is MList.NIL -> MList.Node(last, list)
    is MList.Node -> MList.Node(list.head(),append(list.tail(), last))
}
fun main() {
    val x = MList.NIL<Int>()
    println(x)

    val y = MList.Node(7,x)
    println(y)

    val z = MList.Node(5, y)
    println(z)

    println(reverseHelper(MList.Node<Int>(10, z), MList.empty()))
}
