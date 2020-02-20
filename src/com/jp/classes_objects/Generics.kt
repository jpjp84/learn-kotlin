package com.jp.classes_objects

fun main() {

}

interface Source<out T> {
    fun nextT(): T
}

fun showSource(value: Source<String>) {
//    val src: Source<String?> = value
    val src: Source<Any> = value
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
    val y: Source3<String> = x
}

