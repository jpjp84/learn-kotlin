package com.jp.algorithm.problem


/*
000     010     110     001
100  -> 100 ->  001 ->  001
011     100     000     010

(00) -> (02)
(01) -> (12)
(02) -> (22)
----
(10) -> (01)
(11) -> (11)
(12) -> (21)
----
(20) -> (00)
(21) -> (10)
(22) -> (20)
----------------------
0000    0010
1000    0100
0110 -> 1100
0011    1000
     -> (y, size-x)
(00) -> (03)
(01) -> (13)
(02) -> (23)
(03) -> (33)
----
(10) -> (02)
(11) -> (12)
(12) -> (22)
(13) -> (32)
 */
fun main() {
    val key = arrayOf(
            intArrayOf(1, 0, 0),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 1, 0)
    )
    val lock = arrayOf(
            intArrayOf(0, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(1, 1, 1, 1)
    )
    println("Solution : ${LockAndKey().solution(key, lock)}")
}

class LockAndKey {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        var currentKey = key
        var index = 0
        var answer = false

        while (index++ < 3 && !answer) {
            loop@ for (x in lock.indices) {
                for (y in lock.indices) {
                    if (isMatchKey(lock, currentKey, x, y)) {
                        answer = true
                        break@loop
                    }
                }
            }

            if (!answer) {
                currentKey = rotateMap(currentKey)
            }
        }

        return answer
    }

    private fun isMatchKey(lock: Array<IntArray>, currentKey: Array<IntArray>, x: Int, y: Int): Boolean {
        val match = 1.shl(lock.size).minus(1)
        return lock.mapIndexed { index, ints ->
            val a = ints.joinToString(separator = "").toInt(2)
            if (currentKey.size <= index) {
                return@mapIndexed a == match
            }
            val b = if (x > index) 0 else currentKey[index - x].joinToString(separator = "").toInt(2).shr(y)
            return@mapIndexed a.xor(b) == match
        }.all { it }
    }

    private fun rotateMap(keys: Array<IntArray>): Array<IntArray> {
        val newMap = Array(keys.size) { IntArray(keys.size) { 0 } }
        keys.mapIndexed { x, ints ->
            ints.mapIndexed { y, number ->
                newMap[y][keys.size - 1 - x] = number
            }
        }
        return newMap
    }
}
