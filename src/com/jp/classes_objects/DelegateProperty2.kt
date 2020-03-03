package com.jp.classes_objects

fun main() {
    val lazyPropertyClass = LazyValueClass()
    lazyPropertyClass.lazyValue
    println(LazyValueClass().lazyValue)
}
