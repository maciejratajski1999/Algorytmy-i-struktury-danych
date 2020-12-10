package lista4

import java.util.Arrays.toString

//początek kolejki jest na początku listy
class FrontQueue<X>(val list: MList<X> = MList.NIL()) {
    fun enqueue(new: X): FrontQueue<X> = FrontQueue(list.append(new))
    fun dequeue(): FrontQueue<X> = FrontQueue(list.tail())
    fun top(): X? = list.head()
    override fun toString(): String = list.toString()
    fun isEmpty() = this.top() == null
}

data class FullQueue<X>(val enqueueList: MList<X> = MList.empty(), val dequeueList: MList<X> = MList.empty()) {
    fun enqueue(new: X): FullQueue<X> = copy(enqueueList = enqueueList.prepend(new))
    fun dequeue(): FullQueue<X> = when (dequeueList) {
        is MList.NIL<X> -> copy(enqueueList = MList.empty(), dequeueList = enqueueList.reverse().tail())
        else -> copy(dequeueList = dequeueList.tail())
    }

    fun top(): X? = when (dequeueList) {
        is MList.NIL<X> -> when (enqueueList) {
            is MList.NIL<X> -> null
            is MList.Node<X> -> enqueueList.last()
        }
        else -> dequeueList.head()
    }

    override fun toString(): String = enqueueList.toString() + " / " + dequeueList.toString()
}

fun main() {
    val x = FullQueue<Int>()
    val y = x.enqueue(1)
    val z = y.enqueue(2)
    val bigList = z.enqueue(10).enqueue(11).enqueue(-2)
    println(bigList)
    println(bigList.dequeue())
}
