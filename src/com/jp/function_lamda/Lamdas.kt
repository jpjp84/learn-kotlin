package com.jp.function_lamda

fun main() {
    val arrays = arrayListOf("test1", "test2", "test3", "test4", "test5")
    arrays.fold(mutableListOf()) { acc: MutableList<String>, i: String ->
        println("acc = $acc, i = $i, ")
        acc.add(i)
        // The last expression in a lambda is considered the return value:
        acc
    }
}

//fun <T, R> Collection<T>.fold(
//        initial: R,
//        combine: (acc: R, nextElement: T) -> R
//): R {
//    var accumulator: R = initial
//    for (element: T in this) {
//        accumulator = combine(accumulator, element)
//    }
//    return accumulator
//}