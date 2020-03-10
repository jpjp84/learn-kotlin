package com.jp.coroutines

import kotlinx.coroutines.*

fun main() {
    cancelationTest()
    println()
    cancelationCooperativeTest()
    println()
    noncancleBlockTest()
    println()
    timeoutTest()
}

fun cancelationTest() = runBlocking {
    val job = launch {
        try {
            val repeatLaunch = repeat(1000) {
                delay(500)
                println("running in coroutine scope, $it")
            }
            println("outer repeat launch $repeatLaunch")
        } catch (e: Exception) {
            println("job launch catch ${e.message}")
        } finally {
            println("job launch finally")
        }
    }

    delay(1300)
    println("Canceling...")
    job.cancel()
    job.join()
//     job.cancelAndJoin()
    println("end job")
}

fun cancelationCooperativeTest() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

fun noncancleBlockTest() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

fun timeoutTest() = runBlocking {
    try {
        withTimeout(2000) {
            repeat(1000) {
                delay(300)
                println("not yet timeout...")
            }
        }
    } catch (e: TimeoutCancellationException) {
        println("timeout exception")
    } finally {
        println("timeout!!")
    }
}