package com.jp.algorithm.problem.implements

/*
 * Complete the 'nonDivisibleSubset' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER k
 *  2. INTEGER_ARRAY s
 */

fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
    val newS = s.map { it % k }.groupBy { it }
    var count = s.size
    var countEqual = 0
    println(newS)
    newS.map {
        if (k - it.key == it.key || it.key == 0) {
            count -= it.value.size - 1
        } else if (newS.containsKey(k - it.key) && newS[k - it.key]!!.size > it.value.size) {
            count -= it.value.size
        } else if (newS.containsKey(k - it.key) && newS[k - it.key]!!.size == it.value.size) {
            countEqual += it.value.size
        }
    }
    return count + (countEqual / 2)
}

@ExperimentalStdlibApi
fun main(args: Array<String>) {


//    val first_multiple_input = readLine()!!.trimEnd().split(" ")
//
//    val n = first_multiple_input[0].toInt()
//
//    val k = first_multiple_input[1].toInt()
//
//    val s = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

//    val result = nonDivisibleSubset(5, arrayOf(2, 7, 12, 17, 22))
    val result = nonDivisibleSubset(4, arrayOf(1, 3, 5, 6, 7, 8, 10, 12, 15, 26, 74, 55, 235, 467))
    println(result)
}


fun countBit() {
    val a = intArrayOf(0, 1, 2, 3, 4, 5, 6)
    var b = 591

    val ele = mutableListOf<Int>()
    for (i in 6 downTo 0) {
        println(Integer.toBinaryString(b))
        ele.add(Integer.toBinaryString(b).lastIndexOf('1'))
        b -= (b.and(-b))
    }
    println("Array : ${ele}")
}

fun countBit(set: Int) {
    val subset = mutableListOf<Int>()
    var curSet: Int = set
    while (curSet > 0) {
        subset.add(curSet)
        curSet = (curSet - 1).and(set)
    }
    subset.map {
        println(Integer.toBinaryString(it))
    }
}
