package com.jp.algorithm.problem

/*
 * Complete the 'nonDivisibleSubset' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER k
 *  2. INTEGER_ARRAY s
 */

fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
    val newS = s.map { it % k }
    val n = 1.shl(newS.size) - 1
    var currentN = n
    var maxSubSetSize = 0


    while (currentN > 0) {
        val binaryString = "%${s.size}s".format(Integer.toBinaryString(currentN)).replace(" ", "0")
        if (binaryString.count { it == '1' } >= 2) {
            val subsetS = newS.filterIndexed { index, _ -> binaryString[index] == '1' }
            var isMatch = true
            outer@ for (i in 0..subsetS.size - 2) {
                for (j in i + 1 until subsetS.size) {
                    if ((subsetS[i] + subsetS[j]) % k == 0) {
                        isMatch = false
                        break@outer
                    }
                }
            }

            if (isMatch) {
                maxSubSetSize = maxSubSetSize.coerceAtLeast(binaryString.count { it == '1' })
            }
        }

        currentN = (currentN - 1).and(n)
    }
    return maxSubSetSize
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

    val result = nonDivisibleSubset(5, arrayOf(2, 7, 12, 17, 22))
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
