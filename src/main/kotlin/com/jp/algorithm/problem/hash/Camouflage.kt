package com.jp.algorithm.problem.hash


/**
 *
 */

fun main() {
    val cloths = arrayOf(
            arrayOf("yellow_hat", "headgear"),
            arrayOf("green_turban", "headgear"),
            arrayOf("blue_sunglasses", "eyewear")
    )
    println("Solution : ${Camouflage().solution(cloths)}")
}

class Camouflage {

    fun solution(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it[1] }.values.map { it.size + 1 }.reduce(Int::times) - 1
    }
}
