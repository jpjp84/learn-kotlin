package com.jp.algorithm.problem.hash


/**
 *
 */

fun main() {
    val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)
    println("Solution : ${BestAlbum().solution(genres, plays).toList()}")
}

class BestAlbum {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.zip(plays.toList())
                .mapIndexed { index, pair -> Triple(pair.first, index, pair.second) }
                .groupBy { it.first }
                .toList()
                .sortedByDescending { genreList -> genreList.second.sumBy { it.third } }
                .toMap()
                .values
                .flatMap { genreList ->
                    genreList.sortedByDescending { it.third }
                            .filterIndexed { index, _ -> index < 2 }
                            .map { it.second }
                }
                .toIntArray()
    }
}
