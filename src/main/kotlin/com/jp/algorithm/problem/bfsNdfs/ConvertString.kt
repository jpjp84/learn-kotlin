package com.jp.algorithm.problem.bfsNdfs

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

class ConvertString {
    private var answer: Int = 0

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target)) {
            return 0
        }

        val stack = Stack<Pair<String, Int>>().apply { add(Pair(begin, 0)) }
        val visit = Array(words.size) { false }
        findTargetByDfs(target, stack, visit, words)

        return answer
    }

    private fun findTargetByDfs(target: String, stack: Stack<Pair<String, Int>>, visit: Array<Boolean>, words: Array<String>) {
        val frontWord = stack.peek()

        words.mapIndexed { index, word ->
            if (visit[index] || answer > 0) {
                return@mapIndexed
            }

            val sameCount = frontWord.first.zip(word).filter { it.first != it.second }.count()
            if (sameCount == 1 && target == word) {
                answer = frontWord.second
                return
            }
            if (sameCount == 1) {
                visit[index] = true
                stack.push(Pair(word, frontWord.second + 1))
                findTargetByDfs(target, stack, visit, words)
            }
        }
    }
}
