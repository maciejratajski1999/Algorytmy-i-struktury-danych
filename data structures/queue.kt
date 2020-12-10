package lista4
//początek kolejki jest na początku listy
class FrontQueue<X>(val list: MList<X> = MList.NIL()){
    fun enqueue(new: X):FrontQueue<X> = FrontQueue(append(list, new))
    fun dequeue(): FrontQueue<X> = FrontQueue(list.tail())
    fun top(): X? = list.head()
}

fun main(){
    val x = FrontQueue<Int>()
    val y = x.enqueue(1)
    val z = y.enqueue(2)
    val bigList = z.enqueue(10).enqueue(11).enqueue(-2)
    println(bigList.dequeue().list)
}