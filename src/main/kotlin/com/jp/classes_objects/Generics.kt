package com.jp.classes_objects

fun main() {
    genericTest()

}

fun copy(from: Array<out Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}

fun genericTest() {
    val ints: Array<Int> = arrayOf(1, 2, 3)
    val any = Array<Any>(3) { "" }
    copy(ints, any)
}

interface Source<T> {
    fun nextT(): T
}

fun showSource(value: Source<Any>) {
//    val src: Source<String?> = value
    val src: Source<in String> = value
}

interface Source2<in T> {
    fun nextT(other: T): Int
}

fun showSource2(x: Source2<Any>) {
    x.nextT(1.1)
    val y: Source2<String> = x
}

class Source3<in T> {
    fun nextT(outher: T): Int {
        return 3
    }
}

fun showSource3(x: Source3<Any>) {
    x.nextT(3)
    val y: Source3<String> = x
    y.nextT("test")
}

fun showSourceByUnknownType(x: MutableList<*>) {
//    x.add("Test")
}

fun showSourceByAny(x: MutableList<Any>) {
    x.add("Test")
}
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}