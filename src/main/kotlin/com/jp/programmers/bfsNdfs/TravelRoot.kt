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
    var result = mutableListOf<List<String>>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        val travelStack: Stack<Array<String>> = Stack()
        val visit = Array(tickets.size) { it == 0 }
        travelStack.push(tickets[0])

        bfsSearch(tickets, travelStack, visit)

        return compareResultASC()
    }

    private fun bfsSearch(tickets: Array<Array<String>>, ticketStack: Stack<Array<String>>, visit: Array<Boolean>) {

        tickets.mapIndexed { index, ticket ->
            if (visit[index]) {
                return@mapIndexed
            }

            val topTicket = ticketStack.peek()
            if (topTicket[1] == ticket[0]) {
                findNextRoot(tickets, ticketStack, visit, ticket, index)
            }

            val bottomTicket = ticketStack[0]
            if (bottomTicket[0] == ticket[1]) {
                findNextRoot(tickets, ticketStack, visit, ticket, index, true)
            }
        }

        if (visit.contains(false)) {
            return
        }

        val currentResult = ticketStack.map { it[0] } + ticketStack.peek()[1]
        if (result.contains(currentResult)) {
            return
        }

        result.add(currentResult)
    }

    private fun findNextRoot(
            tickets: Array<Array<String>>,
            ticketStack: Stack<Array<String>>,
            visit: Array<Boolean>,
            ticket: Array<String>,
            index: Int,
            isInsert: Boolean = false) {

        val copiedTicketStack = ticketStack.copy().apply {
            if (isInsert) add(0, ticket) else push(ticket)
        }
        val copiedVisit = visit.copyOf().apply { this[index] = true }

        bfsSearch(tickets, copiedTicketStack, copiedVisit)
    }

    private fun compareResultASC(): Array<String> {
        return result.reduce { acc, list ->
            println("${acc.toList()}, ${list.toList()}")
            acc.zip(list).filter { it.first != it.second }.map {
                if (it.first > it.second) {
                    return@reduce list
                }
                return@reduce acc
            }

            return@reduce acc
        }.toTypedArray()
    }

    private fun <E> Stack<E>.copy(): Stack<E> {
        val tempStack: Stack<E> = Stack()
        tempStack.addAll(this)
        return tempStack
    }
}

