import junit.framework.TestCase

class LinkedListTest : TestCase() {
    fun testTailFunction() {
        val list = LinkedList.of("a", "b", "c")
        assertEquals(LinkedList.tail(list), LinkedList.of("b", "c"))
        assertEquals(LinkedList.tail(LinkedList.Nil), LinkedList.Nil)
    }

    fun testSetHead() {
        val list = LinkedList.of("a", "b", "c")
        assertEquals(LinkedList.setHead(list, "g"), LinkedList.of("g", "a", "b", "c"))
    }

    fun testDrop() {
        val list = LinkedList.of("a", "b", "c", "d", "e")
        assertEquals(LinkedList.drop(list, 3), LinkedList.of("d", "e"))
        assertEquals(LinkedList.drop(LinkedList.Nil, 3), LinkedList.Nil)
    }

    fun testDropWhile() {
        val list = LinkedList.of("a", "a", "c", "d", "e")
        assertEquals(LinkedList.dropWhile(list) { x -> x == "a" }, LinkedList.of("c", "d", "e"))
    }

    fun testHasSubsequence() {
        val list = LinkedList.of("a", "b", "c", "e", "f", "g")
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("a")))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("b")))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("c")))
        assertFalse(LinkedList.hasSubsequence(list, LinkedList.of("d")))
        assertFalse(LinkedList.hasSubsequence(list, LinkedList.of("d")))
        assertFalse(LinkedList.hasSubsequence(LinkedList.Nil, LinkedList.of("a")))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.Nil))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("a", "b")))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("b", "c")))
        assertTrue(LinkedList.hasSubsequence(list, LinkedList.of("e", "f")))
        assertFalse(LinkedList.hasSubsequence(list, LinkedList.of("a", "g")))
    }

    fun testFoldRightSum() {
        val sum = LinkedList.foldRight(LinkedList.of(1.0, 2.0, 3.0), 0.0, { a, b -> a + b })
        assertEquals(6.0, sum)
    }



    fun testMap() {
        val list = LinkedList.of(1, 2, 3, 1)
        val expected = LinkedList.of("NOPE", "NOPE", "YUP", "NOPE")
        assertEquals(expected, LinkedList.map(list) { x -> if (x > 2) "YUP" else "NOPE" })
    }

    fun testFlatMap() {
        val list = LinkedList.of(1, 2, 3)
        val expected = LinkedList.of(1, 1, 2, 2, 3, 3)
        assertEquals(expected, LinkedList.flatMap(list) { i -> LinkedList.of(i, i) })
    }

    fun testFilter() {
        val list = LinkedList.of(1, 2, 3, 1, -4, -5, 6, 7, -1)
        val expected = LinkedList.of(1, 2, 3, 1, 6, 7)
        assertEquals(expected, LinkedList.filter(list) { x -> x >= 0 })
    }


    fun testAppend() {
        val list = LinkedList.of(1, 2, 3)
        assertEquals(LinkedList.of(1, 2, 3, 4), LinkedList.append(list, LinkedList.of(4)))
    }
    fun testIncrement() {
        val list = LinkedList.of(1, 2, 3)
        assertEquals(LinkedList.of(2, 3, 4), LinkedList.increment(list))
    }
    fun testStringify() {
        val list = LinkedList.of(1.0, 2.0, 3.3)
        assertEquals(LinkedList.of("1.0", "2.0", "3.3"), LinkedList.stringify(list))
    }
    fun testFoldLeft() {
        val sum = LinkedList.foldLeft(LinkedList.of(1, 2, 3), 0, { a, b -> a + b })
        assertEquals(6, sum)

        val product = LinkedList.foldLeft(LinkedList.of(1, 2, 3), 1.0, { a, b -> a * b })
        assertEquals(6.0, product)

        val reverse = LinkedList.foldLeft(LinkedList.of(1, 2, 3), LinkedList.empty<Int>(), { a, b -> LinkedList.Cons(b, a) })
        assertEquals(LinkedList.of(3, 2, 1), reverse)
    }

    fun testFoldRightProduct() {
        val product = LinkedList.foldRight(LinkedList.of(4.0, 2.0, 3.0), 1.0, { a, b -> a * b })
        assertEquals(24.0, product)
    }

    fun testFoldRightNil() {
        val res = LinkedList.foldRight(LinkedList.of(1, 2, 3), LinkedList.empty<Int>(), { a, b -> LinkedList.Cons(a, b) })
        assertEquals(LinkedList.of(1, 2, 3), res)
    }

    fun testLength() {
        val len = LinkedList.length(LinkedList.of(1.0, 2.0, 3.0))
        assertEquals(3, len)
    }
}