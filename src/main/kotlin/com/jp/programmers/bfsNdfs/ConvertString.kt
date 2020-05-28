package com.jp.programmers.bfsNdfs

import java.util.*


/**
 * https://www.notion.so/Remove-Element-cd10ee8700444901af87aaf33220c45a
 */

fun main() {
    val begin = "hit"
    val target = "cog"
    val words = arrayOf("hot", "dot", "dog", "lot", "log", "cog")
    println("Solution : ${ConvertString().solution(begin, target, words)}")
}

//[hit]       []            [hit]
//[hot]       [hit]         [hit, hot]
//[dot, lot]  [hit, hot]    [hit, hot], [hit, hot, dot], [hit, hot, lot]
//---
//[dot, log]  [hit, hot, lot]
//[dot, cog]  [hit, hot, lot, log, cog]
//---
//[dog]       [hit, hot, dot]
//[cog]       [hit, hot, dot, dog]


class ConvertString {
    private var answer: Int = 0

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target)) {
            return 0
        }

        answer = words.size
        val stack = Stack<String>().apply { add(begin) }

        val visit = Array(words.size) { false }
        findTargetByDfs(target, stack, visit, words)

        return answer
    }

    private fun findTargetByDfs(target: String, stack: Stack<String>, visit: Array<Boolean>, words: Array<String>) {
        val frontWord = stack.peek()

        words.mapIndexed { index, word ->
            if (visit[index]) {
                return@mapIndexed
            }

            val sameCount = frontWord.zip(word).filter { it.first == it.second }.count()
            if (sameCount == 2 && target == word) {
                answer = answer.coerceAtMost(stack.size)
                return
            }
            if (sameCount == 2) {
                visit[index] = true

                val tempStack: Stack<String> = Stack()
                tempStack.addAll(stack)
                tempStack.add(word)
                val tempVisit = visit.copyOf()
                findTargetByDfs(target, tempStack, tempVisit, words)
            }
        }
    }
}
