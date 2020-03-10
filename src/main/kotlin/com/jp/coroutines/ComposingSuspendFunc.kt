package com.jp.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
//    sequentiallySuspendFunction()
//    println()
//    asyncSuspendFunction()
//    println()
//    acyncStyleFuncTest()
    println()
    returnAsyncFunc()
}

fun sequentiallySuspendFunction() = runBlocking {
    val startTime = System.currentTimeMillis()
    val a = oneFunction()
    val b = twoFunction()

    println("sum = ${a + b}")
    println("execute time : ${System.currentTimeMillis() - startTime}")
}

fun asyncSuspendFunction() = runBlocking {
    val startTime = System.currentTimeMillis()
    val a = async { oneFunction() }
    val b = async { twoFunction() }
    val c = async(start = CoroutineStart.LAZY) { twoFunction() }

    println("sum1 = ${a.await() + b.await()}")
    println("sum2 = ${a.await() + b.await() + c.await()}")
    c.start()
    println("sum3 = ${a.await() + b.await() + c.await()}")
    println("execute time : ${System.currentTimeMillis() - startTime}")
}

suspend fun oneFunction(): Int {
    delay(1000)
    return 10
}

suspend fun twoFunction(): Int {
    delay(2000)
    return 20
}

fun acyncStyleFuncTest() {
    val time = measureTimeMillis {
        val a = threeFunctionAsync()

        runBlocking {
            println(a.await())
        }
    }
    println("execute time : $time")
}

suspend fun threeFunction(): Int {
    delay(1000)
    return 20
}

fun threeFunctionAsync() = GlobalScope.async {
    threeFunction()
}

suspend fun getAsyncFuncResult() = coroutineScope {
    val a = async { oneFunction() }
    val b = async { twoFunction() }
    a.await() + b.await()
}

fun returnAsyncFunc() = runBlocking {
    val time = measureTimeMillis {
        println("Result1 : ${getAsyncFuncResult()}")
    }

    println("execute time : $time")
}
