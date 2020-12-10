import java.io.File

fun characterCounter(path: String) = File(path).inputStream().readBytes()
    .groupBy { it }.mapValues { (_, v) -> v.size}
    .mapKeys { (k,_) -> "'${k.toChar()}' " }

fun main(argz: Array<String>){
    argz.forEach { filename -> println(characterCounter(filename)).also { println(filename) } }
}