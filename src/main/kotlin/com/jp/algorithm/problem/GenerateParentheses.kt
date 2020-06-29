package com.jp.algorithm.problem

import java.util.*

/**
 * https://www.notion.so/Generate-Parentheses-610eb0b10f8544fd9b7ecfaa65574390
 */

fun main() {
    println("Solution : ${GenerateParentheses().generateParenthesis(1)}")
}

class GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {
        val answer = mutableListOf<String>()
        val stack = Stack<String>()
        stack.push("(")

        while (!stack.isEmpty()) {
            val popStack = stack.pop()
            if (popStack.length == n * 2) {
                answer.add(popStack)
                continue
            }

            val countOpen = popStack.count { it == '(' }
            val countClose = popStack.count { it == ')' }

            if (countOpen < n) {
                stack.push("${popStack}(")
            }

            if (countClose < n && countOpen > countClose) {
                stack.push("${popStack})")
            }
        }

        return answer
    }

    fun generateParenthesisByBitwise(n: Int): List<String> {
        val answer = mutableListOf<String>()
        for (i in 0..1.shl(n * 2)) {
            val binaryString = Integer.toBinaryString(i)

            if (binaryString[0] != '1' || binaryString.length != n * 2) {
                continue
            }
            val count0 = binaryString.count { it == '0' }
            val count1 = binaryString.count { it == '1' }
            if (count0 != count1 || !isMatchParenthesis(binaryString)) {
                continue
            }
            answer.add(binaryString.replace("""[1]""".toRegex(), "(").replace("""[0]""".toRegex(), ")"))
        }
        return answer
    }

    private fun isMatchParenthesis(binaryString: String): Boolean {
        val stack = Stack<Char>()
        binaryString.forEach {
            if (it == '1') {
                stack.push(it)
                return@forEach
            }

            if (stack.empty()) {
                return false
            }
            stack.pop()
        }

        return true
    }
}
