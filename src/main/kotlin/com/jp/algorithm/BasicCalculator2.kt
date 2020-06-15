package com.jp.algorithm

import java.util.*

/**
 * 8:47
 */

fun main() {
//    val s = "1+1+1"
//    val s = "3+2*2+3+42+2*3/2-3+4*14/2"
//    val s = " 3/2 "
    val s = "0-2147483647"
    println("Solution : ${BasicCalculator2().calculate(s)}")
}

class BasicCalculator2 {

    fun calculate(s: String): Int {
        val nonSpaceS = s.replace("\\s".toRegex(), "")
        return """([+\-]?[\d*/]+)""".toRegex().findAll(nonSpaceS).toList().fold(0) { acc, group ->
            var token = group.groupValues[1]
            if (token.contains("""[*/]""".toRegex())) {
                token = operatingMultipleOrDivide(token)
            }

            when (token.first()) {
                '+' -> acc + token.substring(1).toInt()
                '-' -> acc - token.substring(1).toInt()
                else -> token.toInt()
            }
        }
    }

    private fun operatingMultipleOrDivide(s: String): String {
        val calculatedValue = """([*/]?[\d]+)""".toRegex().findAll(s).toList().fold(0) { acc, group ->
            val token = group.groupValues[1]
            when (token.first()) {
                '*' -> acc * token.substring(1).toInt()
                '/' -> acc / token.substring(1).toInt()
                else -> token.toInt()
            }
        }
        return if (s.first() in "+-") "${s.first()}$calculatedValue" else "$calculatedValue"
    }

    fun calculate2(s: String): Int {
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
}
