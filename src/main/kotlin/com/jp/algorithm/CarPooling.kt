package com.jp.algorithm

import java.util.*

/**
 *
 */

fun main() {
    val trips = arrayOf(
            intArrayOf(7, 5, 6),
            intArrayOf(6, 7, 8),
            intArrayOf(10, 1, 6)
    )
    val capacity = 16
//    val trips = arrayOf(
//            intArrayOf(2, 2, 6),
//            intArrayOf(2, 3, 4),
//            intArrayOf(2, 4, 7),
//            intArrayOf(8, 6, 7)
//    )
//    val capacity = 11

    println("Solution : ${CarPooling().carPooling(trips, capacity)}")
}

class CarPooling {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        val getVehicle = updateVehicle(capacity)
        trips.sortedBy { it[1] }.fold(mutableListOf<IntArray>()) { vehicle, trip ->
            getVehicle(vehicle, trip) ?: return false
        }
        return true
    }

    private fun updateVehicle(capacity: Int): (MutableList<IntArray>, IntArray) -> MutableList<IntArray>? {
        return out@{ vehicle, trip ->
            vehicle.filter { passenger -> trip[1] < passenger[2] }
                    .toMutableList()
                    .apply {
                        if (sumBy { it[0] } + trip[0] > capacity) return@out null
                        add(trip)
                    }
        }
    }
}
