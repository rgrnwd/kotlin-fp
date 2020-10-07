sealed class LinkedList<out A> {
    companion object {
        fun <A> of(vararg aa: A): LinkedList<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): LinkedList<A> = Nil

        fun <A> length(xs: LinkedList<A>): Int =
            foldLeft(xs, 0, { a, _ -> 1 + a })

        fun <A> drop(l: LinkedList<A>, n: Int): LinkedList<A> =
            if (n == 0) l
            else when (l) {
                is Cons -> drop(l.tail, n - 1)
                is Nil -> l
            }

        fun <A> dropWhile(l: LinkedList<A>, f: (A) -> Boolean): LinkedList<A> =
            when (l) {
                is Cons ->
                    if (f(l.head)) dropWhile(l.tail, f) else l
                is Nil -> l
            }

        fun <A> tail(x: LinkedList<A>) : LinkedList<A> {
            when (x) {
                is Nil -> return Nil
                is Cons -> return x.tail
            }
        }
        fun <A> setHead(x: LinkedList<A>, head: A) : LinkedList<A> {
            when (x) {
                is Nil -> return Nil
                is Cons -> return Cons(head, x)
            }
        }

        fun increment(x: LinkedList<Int>) : LinkedList<Int> =
            foldRight(x, empty(), { i: Int, b -> Cons(i + 1, b)})

        fun <A> zipWith(a: LinkedList<A>, b: LinkedList<A>, f: (A, A) -> A) : LinkedList<A> =
            when (a) {
                is Nil -> a
                is Cons -> {
                    when (b) {
                        is Nil -> b
                        is Cons -> Cons(f(a.head, b.head), zipWith(a.tail, b.tail, f))
                    }
                }
            }

        tailrec fun <A> hasSubsequence(xs: LinkedList<A>, sub: LinkedList<A>): Boolean =
            when (sub) {
                is Nil -> true
                is Cons -> {
                    when (xs) {
                        is Nil -> false
                        is Cons -> {
                            if (xs.head == sub.head && hasSubsequenceInternal(xs.tail, sub.tail))
                                true
                            else
                                hasSubsequence(xs.tail, sub)
                        }
                    }
                }
            }
        tailrec fun <A> hasSubsequenceInternal(xs: LinkedList<A>, sub: LinkedList<A>): Boolean =
            when (sub) {
                is Nil -> true
                is Cons -> {
                    when (xs) {
                        is Nil -> false
                        is Cons -> {
                            (xs.head == sub.head && hasSubsequence(xs.tail, sub.tail))
                        }
                    }
                }
            }

        fun stringify(x: LinkedList<Double>) : LinkedList<String> =
            foldRight(x, empty(), { i: Double, b -> Cons(i.toString(), b)})

        fun <A> append(x: LinkedList<A>, y: LinkedList<A>) : LinkedList<A> =
            foldRight(x, y, { a, b -> Cons(a, b)})

        tailrec fun <A, B> foldLeft(xs: LinkedList<A>, z: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }

        fun <A, B> map(xs: LinkedList<A>, f: (A) -> B): LinkedList<B> =
            foldRight(xs, empty(), { a, b -> Cons(f(a), b) })

        fun <A, B> flatMap(xa: LinkedList<A>, f: (A) -> LinkedList<B>): LinkedList<B> =
            foldRight(xa, empty(), { a, b -> append(f(a), b) })

        fun <A> filter(xs: LinkedList<A>, f: (A) -> Boolean): LinkedList<A> =
            flatMap(xs) { a -> if (f(a)) of(a) else empty()}

        fun <A, B> foldRight(xs: LinkedList<A>, z: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> f(xs.head, foldRight(xs.tail, z, f))
            }
    }
    object Nil : LinkedList<Nothing>()
    data class Cons<out A>(
        val head: A,
        val tail: LinkedList<A>
    ) : LinkedList<A>()
}

