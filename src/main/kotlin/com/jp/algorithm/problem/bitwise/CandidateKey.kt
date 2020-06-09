package com.jp.algorithm.problem.bitwise

fun main() {
    val relation = arrayOf(
            arrayOf("100", "ryan", "music", "2"),
            arrayOf("200", "apeach", "math", "2"),
            arrayOf("300", "tube", "computer", "3"),
            arrayOf("400", "con", "computer", "4"),
            arrayOf("500", "muzi", "music", "3"),
            arrayOf("600", "apeach", "music", "2")
    )
//    [(1, 5)(2, 3), 0, 4], [(0, 4, 5), (1), (2, 3)], [(0, 1), (2, 4), 3, 5]
    println("Solution : ${CandidateKey().solution(relation)}")
}

class CandidateKey {
    fun solution(relation: Array<Array<String>>): Int {
        val n = relation[0].size
        val candidateKey = mutableListOf<Int>()

        for (i in 1 until 1.shl(n)) {
            if (!isMinimal(i, candidateKey)) {
                continue
            }

            if (isUnique(relation, n, i)) {
                candidateKey.add(i)
            }

        }
        return 0
    }

    private fun isUnique(relation: Array<Array<String>>, n: Int, i: Int): Boolean {
        val set = mutableSetOf<String>()
        relation.map {
            var temp = ""
            for (j in 0 until n) {
                if (i.and(1.shl(j)) != 0) {
                    temp += "${it[j]} "
                }
            }
            set.add(temp)
        }
        return set.size == relation.size
    }

    private fun isMinimal(index: Int, candidateKey: MutableList<Int>): Boolean {
        for (key in candidateKey) {
            if (key.and(index) == key) {
                return false
            }
        }
        return true
    }
}
