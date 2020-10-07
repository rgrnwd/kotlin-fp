sealed class Tree<out A> {
    fun size(): Int = fold(this, { 1 }, { a, b -> a + b })

    fun depth(): Int = fold(this, { 0 }, { a, b -> 1 + maxOf(a, b)})

    companion object {
        fun maximum (tree: Tree<Int>) : Int =
            fold(tree, { a -> a }, { a, b -> maxOf(a, b) })

        fun <A, B> map(tree: Tree<A>, f: (A) -> B): Tree<B> =
            fold(tree, { a -> Leaf(f(a))}, { a: Tree<B>, b: Tree<B> -> Branch(a, b) })

        private fun <A, B> fold(tree: Tree<A>, z: (A) -> B, f: (B, B) -> B): B =
            when (tree) {
                is Leaf -> z(tree.value)
                is Branch -> f(fold(tree.left, z, f), fold(tree.right, z, f))
            }
    }

    data class Leaf<A>(val value: A) : Tree<A>()

    data class Branch<A>(
        val left: Tree<A>,
        val right: Tree<A>
    ) : Tree<A>()
}