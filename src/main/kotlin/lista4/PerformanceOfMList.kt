package lista4

fun main(){
    val tries = 10000000
    for (z in 1..10) {

        run {
            val timer = System.currentTimeMillis()

            var myQueue = FullQueue<Int>()
            for (i in 0..tries) {
                myQueue = myQueue.enqueue(i)
            }
            for (i in 0..tries) {
                myQueue = myQueue.dequeue()
            }
            println(System.currentTimeMillis() - timer)
        }
        System.gc()
        Thread.sleep(2000)
    }

}
// for tries = 100000 : 38608  <-- naive append
// for tries = 100000 : 38009  <-- naive append v.2
// for tries = 100000 : 35     <-- FullQueue
// for tries = 10000000 : 11503 <-- FullQueue
