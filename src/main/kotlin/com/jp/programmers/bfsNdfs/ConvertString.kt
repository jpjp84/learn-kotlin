package com.jp.programmers.bfsNdfs

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val begin = "hittte"
    val target = "cogged"
    val words = arrayOf("hottte", "adottt", "hogtte", "lgttet", "logtte", "cogtte", "coggte", "loggte", "cogged", "cogged", "coggee" )
    println("Solution : ${ConvertString().solution(begin, target, words)}")
}

class ConvertString {
    private var answer: Int = Int.MAX_VALUE

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target)) {
            return 0
        }

        val stack = Stack<String>().apply { add(begin) }
        val visit = Array(words.size) { false }
        findTargetByDfs(target, stack, visit, words)

        return if(answer != Int.MAX_VALUE) answer else 0
    }

    private fun findTargetByDfs(target: String, stack: Stack<String>, visit: Array<Boolean>, words: Array<String>) {
        val frontWord = stack.peek()

        words.mapIndexed { index, word ->
            if (visit[index]) {
                return@mapIndexed
            }

            val sameCount = frontWord.zip(word).filter { it.first != it.second }.count()
            if (sameCount == 1 && target == word) {
                answer = answer.coerceAtMost(stack.size)
                return
            }
            if (sameCount == 1) {
                visit[index] = true
                findTargetByDfs(target, stack.copy().apply { push(word) }, visit.copyOf(), words)
            }
        }
    }

    private fun <E> Stack<E>.copy(): Stack<E> {
        val tempStack: Stack<E> = Stack()
        tempStack.addAll(this)
        return tempStack
    }
}
