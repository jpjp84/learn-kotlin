package com.jp.programmers.greedy

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val number = "123456789123456789123456789123456789123456789123456789123456789123456789123456789"
    val k = 1

    //앞에서 부터 차례대로 돌다가 이전보다 큰 수가 나오면 이전 수를 지워준다
    println("Solution : ${MakeBigInt().solution(number, k)}")
}

class MakeBigInt {

    fun solution2(number: String, k: Int): String {
        var answer = ""
        var removeCount = 0
        var index = 0

        while (index < number.length) {
            if (removeCount >= k) {
                answer += number.substring(index)
                break
            }

            val currentNumber = Character.getNumericValue(number[index++])
            if (answer.isEmpty()) {
                answer += currentNumber
                continue
            }

            while (answer.isNotEmpty()) {
                val lastNumber = Character.getNumericValue(answer[answer.length - 1])
                if (currentNumber <= lastNumber || removeCount >= k) break

                answer = answer.dropLast(1)
                removeCount++
            }

            answer += currentNumber
        }

        repeat(k - removeCount) { answer = answer.dropLast(1) }
        return answer
    }

    fun solution(number: String, k: Int): String {
        val stack = Stack<String>()
        var removeCount = 0
        var index = 0

        while (index < number.length) {
            if (removeCount >= k) {
                stack.push(number.substring(index))
                break
            }

            val currentStr = number[index++]
            val currentNumber = Character.getNumericValue(currentStr)
            if (stack.isEmpty() || removeCount >= k) {
                stack.push(currentStr.toString())
                continue
            }

            while (!stack.isEmpty()) {
                val topStackNumber = stack.peek().toInt()
                if (currentNumber <= topStackNumber || removeCount >= k) break

                stack.pop()
                removeCount++
            }

            stack.push(currentStr.toString())
        }

        return stack.fold("") { acc, stackNumber -> acc + stackNumber }.dropLast(k - removeCount)
    }
}
