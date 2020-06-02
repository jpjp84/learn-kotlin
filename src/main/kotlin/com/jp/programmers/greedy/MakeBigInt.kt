package com.jp.programmers.greedy

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val number = "5421345"
    val k = 4

    //앞에서 부터 차례대로 돌다가 이전보다 큰 수가 나오면 이전 수를 지워준다
    println("Solution : ${MakeBigInt().solution(number, k)}")
}

class MakeBigInt {

    fun solution(number: String, k: Int): String {
        var removeCount = 0
        val stack = Stack<Int>()

        number.toList().map {
            val currentNumber = Character.getNumericValue(it)
            if (stack.isEmpty() || removeCount >= k) {
                stack.push(currentNumber)
                return@map
            }

            while (!stack.isEmpty()) {
                val topStackNumber = stack.peek()
                if (currentNumber <= topStackNumber || removeCount >= k) break

                stack.pop()
                removeCount++
            }

            stack.push(currentNumber)
        }

        for (i in 0 until k - removeCount) {
            stack.pop()
        }

        return stack.fold("") { acc, stackNumber -> acc + stackNumber }
    }
}
