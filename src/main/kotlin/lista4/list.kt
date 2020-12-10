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
}
fun <T> append(list: MList<T>, last: T):MList<T> = when(list){
    is MList.NIL -> MList.Node(last, list)
    is MList.Node -> MList.Node(list.head(),append(list.tail(), last))
}


private tailrec fun <T> reverseHelper(list:MList<T>, acc:MList<T>):MList<T> = when (list) {
    is MList.NIL -> acc
    is MList.Node -> reverseHelper(list.tail(), MList.Node(list.head(), acc))
}

fun main() {
    val x = MList.NIL<Int>()
    println(x)

    val y = MList.Node(7,x)
    println(y)

    val z = MList.Node(5, y)
    println(z)

    println(z.tail())
    println(z.tail().head())
    println(x.head())

    val a = z.tail().head()
    if (a != null){
        println(a+1)
    }
    val a1 = append(z,10)
    println(a1)
    println(a1.reverse())

}
