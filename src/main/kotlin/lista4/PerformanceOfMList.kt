package lista4

fun main(){
    val timer = System.currentTimeMillis()
    val tries = 100000
    var myQueue = FrontQueue<Int>()
    for (i in 0..tries){
        myQueue = myQueue.enqueue(i)
    }
    for (i in 0..tries){
        myQueue = myQueue.dequeue()
    }
    println( myQueue.isEmpty())
    println(System.currentTimeMillis() - timer)
}
// for tries = 100000 : 38608  <-- naive append
// for tries = 100000 : 38009  <-- naive append v.2