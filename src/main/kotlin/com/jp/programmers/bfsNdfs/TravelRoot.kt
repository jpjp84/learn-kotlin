package com.jp.programmers.bfsNdfs

import java.util.*


fun main() {
    val tickets2 = arrayOf(
            arrayOf("ICN", "JFK"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND")
    )

    val tickets = arrayOf(
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("ICN", "SFO"),
            arrayOf("SFO", "ATL")
    )
    // [ICN, ATL, ICN, SFO, ATL, SFO]
    /**
     * ATL - ICN - ATL - SFO
     * ATL - ICN - SFO - ATL - SFO
     */


    val tickets3 = arrayOf(
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
            arrayOf("IAD", "ICN"),
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("ICN", "JFK"),
            arrayOf("SFO", "ATL"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND"),

            arrayOf("SFO", "ICN"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
            arrayOf("IAD", "ICN"),
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("ICN", "JFK"),
            arrayOf("SFO", "ATL"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND")
    )
    // [ICN, ATL, ICN, JFK, HND, IAD, ICN, SFO, ATL, SFO]


    val tickets4 = arrayOf(
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ICN", "ATL")
    )
    // [ICN, SFO, ATL, ICN, ATL, SFO]

    println("Solution : ${TravelRoot().solution(tickets4).toList()}")
}

class TravelRoot {
    var answer = arrayOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        tickets.sortBy { it[0] + it[1] }

        tickets.mapIndexed { index, ticket ->
            if (ticket[0] != "ICN") {
                return@mapIndexed
            }
            val travelStack: Stack<Array<String>> = Stack()
            val visit = Array(tickets.size) { it == index }
            travelStack.push(ticket)

            bfsSearch(tickets, travelStack, visit)
        }

        return answer
    }

    private fun bfsSearch(tickets: Array<Array<String>>, ticketStack: Stack<Array<String>>, visit: Array<Boolean>) {
        if (answer.isNotEmpty()) {
            return
        }

        tickets.mapIndexed { index, ticket ->
            if (visit[index]) {
                return@mapIndexed
            }

            if (ticketStack.peek()[1] == ticket[0]) {
                val copiedTicketStack = ticketStack.copy().apply { push(ticket) }
                val copiedVisit = visit.copyOf().apply { this[index] = true }

                bfsSearch(tickets, copiedTicketStack, copiedVisit)
            }
        }

        if (visit.contains(false)) {
            return
        }

        answer = (ticketStack.map { it[0] } + ticketStack.peek()[1]).toTypedArray()
    }

    private fun <E> Stack<E>.copy(): Stack<E> {
        val tempStack: Stack<E> = Stack()
        tempStack.addAll(this)
        return tempStack
    }
}

