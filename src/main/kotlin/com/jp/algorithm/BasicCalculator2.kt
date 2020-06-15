package com.jp.algorithm

import java.util.*

/**
 * 8:47
 */

fun main() {
    val s = "1+1+1"
    println("Solution : ${BasicCalculator2().calculate(s)}")
}

class BasicCalculator2 {
    fun calculate(s: String): Int {
        val nonSpaceS = s.replace("\\s".toRegex(), "")
        val initNumber = substringOperator(nonSpaceS)
        var remainderString = nonSpaceS.removePrefix(initNumber)
        var yetCalculatingToken = "+$initNumber"
        var result = 0

        while (remainderString.isNotEmpty()) {
            var number = substringOperator(remainderString)
            remainderString = remainderString.removePrefix(number)

            if (isHighOrderPrefixOperator(number.first())) {
                val prevNumberSet = yetCalculatingToken
                val prevNumber = prevNumberSet.drop(1)
                val operatingResult = when (number.first()) {
                    '*' -> prevNumber.toInt() * number.substring(1).toInt()
                    '/' -> prevNumber.toInt() / number.substring(1).toInt()
                    else -> number
                }
                number = "${prevNumberSet.first()}$operatingResult"
                yetCalculatingToken = number
                continue
            }

            result = calculatePlusMinus(result, yetCalculatingToken)
            yetCalculatingToken = number
        }
        result = calculatePlusMinus(result, yetCalculatingToken)
        return result
    }

    fun calculatePlusMinus(result: Int, topNumber: String): Int {
        return when (topNumber.first()) {
            '+' -> result + topNumber.substring(1).toInt()
            '-' -> result - topNumber.substring(1).toInt()
            else -> result
        }
    }

    fun isOperator(s: Char): Boolean {
        return s in "+-*/"
    }

    fun isHighOrderPrefixOperator(s: Char): Boolean {
        return s in "*/"
    }

    fun substringOperator(s: String): String {
        var res = ""
        for (i in s.indices) {
            if (i != 0 && isOperator(s[i])) {
                break
            }
            res += s[i]
        }
        return res
    }

    fun reg() {
        //1. 숫자를 reg로 뽑아낸다
        //2. 사칙연산을 뽑아낸다
        //3. 차례로 계산해 나간다.
    }
}
