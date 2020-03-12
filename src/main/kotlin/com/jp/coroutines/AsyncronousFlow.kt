package com.jp.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull


fun main() {
    sequenceListTest()
    println()
    suspendListFuncTest()
    println()
    flowTest()
    println()
    flowColdTest()
    println()
    flowFuncTest()
    println()
    flowOperatorTest()
}

fun sequenceFunc(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(1000)
        yield(i)
    }
    println("sequence end")
}

fun sequenceListTest() = runBlocking {
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }

    sequenceFunc().forEach { value -> println(value) }
}

suspend fun suspendListFunc(): List<Int> {
    delay(1000)
    println("Thread : ${Thread.currentThread().name}")
    return listOf(1, 2, 3)
}

fun suspendListFuncTest() = runBlocking<Unit> {
    suspendListFunc().forEach { value -> println(value) }
}


fun flowFunc(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(200)
        emit(i) // emit next value
    }
}

fun flowTest() = runBlocking<Unit> {
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    flowFunc().collect { value -> println(value) }
}


fun flowColdFunc(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun flowColdTest() = runBlocking {
    println("Calling foo...")
    val flow = flowColdFunc()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

fun flowCancellation(): Flow<Int> = flow {
    repeat(1000) {
        delay(100)
        emit(it)
    }
}

suspend fun flowBuilder(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3, 4, 5, 6)
}

fun flowFuncTest() = runBlocking {
    withTimeoutOrNull(400) {
        val flowCoroutine = flowCancellation()
        flowCoroutine.collect { println("collect test : $it") }
    }
//    delay(400)
//    flowCoroutine.cancel()
// 은 없다..ㅠㅠ

    println()
    flowBuilder().asFlow().collect { println("collect test : $it") }
}

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun flowOperatorTest() = runBlocking {
    println("coroutine start")
    flowBuilder().asFlow()
            .filter { it < 2 }
            .collect { println("value $it") }

    flowBuilder().asFlow()
            .conflate()
            .collect { println("value $it") }

    flowBuilder().asFlow()
            .collectLatest { value -> // cancel & restart on the latest value
//                println("Collecting $value")
                delay(300) // pretend we are processing it for 300 ms
                println("Done $value")
            }

    flowBuilder().asFlow().map { requestFlow(it) }
}