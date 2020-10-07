import junit.framework.TestCase

import org.junit.Assert.*

class TreeTest : TestCase() {
    val tree: Tree<Int> = Tree.Branch(
        Tree.Branch(
            Tree.Leaf(1),
            Tree.Branch(
                Tree.Leaf(2),
                Tree.Leaf(3)
            )
        ),
        Tree.Leaf(4)
    )

    fun testTreeSize() {
        assertEquals(4, tree.size())
    }

    fun testTreeMaximum() {
        assertEquals(4, Tree.maximum(tree))
        assertEquals(6, Tree.maximum(Tree.Leaf(6)))
        assertEquals(9, Tree.maximum(Tree.Branch(Tree.Leaf(1), Tree.Leaf(9))))

    }

    fun testTreeDepth() {
        val tree: Tree<Int> = Tree.Branch(
            Tree.Leaf(4),
            Tree.Branch(
                Tree.Leaf(1),
                Tree.Branch(
                    Tree.Leaf(2),
                    Tree.Branch(
                        Tree.Leaf(3),
                        Tree.Branch(
                            Tree.Leaf(5),
                            Tree.Leaf(6)
                        )
                    )
                )
            )
        )

        assertEquals(5, tree.depth())
    }

    fun testMap() {
        val newTree = Tree.map(tree) { t -> t.toString() }
        assertEquals(newTree, Tree.Branch(
            Tree.Branch(
                Tree.Leaf("1"),
                Tree.Branch(
                    Tree.Leaf("2"),
                    Tree.Leaf("3")
                )
            ),
            Tree.Leaf("4")))
    }
}