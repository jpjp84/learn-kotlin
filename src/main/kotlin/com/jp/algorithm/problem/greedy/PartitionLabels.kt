package com.jp.algorithm.problem.greedy

/**
 * https://www.notion.so/Partition-Labels-2d7710e9368d48c499a9f119b036835a
 */

fun main() {
    println("Solution : ${PartitionLabels().partitionLabels("caedbdedda")}")
}

class PartitionLabels {
    fun partitionLabels(S: String): List<Int> {
        var lastIndex = 0
        var startIndex = 0
        val list = mutableListOf<Int>()

        S.toList().mapIndexed { index, char ->
            val currentLastIndex = S.indexOfLast { char == it }
            if (currentLastIndex > lastIndex) {
                lastIndex = currentLastIndex
            }

            if (index == lastIndex) {
                list.add(lastIndex - startIndex + 1)

                startIndex = lastIndex + 1
                lastIndex = 0
                return@mapIndexed
            }
        }

        return list
    }
}
