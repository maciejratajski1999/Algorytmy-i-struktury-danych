package lista6

sealed class BTree<T : Comparable<T>> {

    abstract fun append(t: T): BTree<T>;

    abstract fun contains(t: T): Boolean
}

class Leaf<T : Comparable<T>> : BTree<T>() {
    override fun toString(): String = "\uD83C\uDF42"
    override fun append(t: T): BTree<T> =
        TreeNode<T>(t, this, this)

    override fun contains(t: T): Boolean = false
}

@Suppress("UNCHECKED_CAST")
data class TreeNode<T : Comparable<T>>(
    val value: T,
    val left: BTree<T> = Leaf<T>(),
    val right: BTree<T> = Leaf<T>()
) : BTree<T>() {
    override fun append(t: T): BTree<T> =
        if (t <= value) {
            TreeNode(value, left.append(t), right)
        } else {
            TreeNode(value, left, right.append(t))
        }

    override fun toString(): String = """($left / $value \ $right)"""
    override fun contains(t: T): Boolean =
        if (t < value) {
            left.contains(t)
        } else if (t > value) {
            right.contains(t)
        } else {
            true
        }


}

@Suppress("UNCHECKED_CAST")
fun <T : Comparable<T>> emptyTree(): BTree<T> = Leaf<T>()


fun main() {
    val empty = emptyTree<Int>()

    println(empty)
    val s1 = empty.append(6)
    println(s1)
    val s2 = s1.append(2).append(1).append(7).append(4).append(1).append(6)
    println(s2)
    println(s2.contains(4))
    println(s2.contains(9))
}


