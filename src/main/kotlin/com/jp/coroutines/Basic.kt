package com.jp.coroutines

import kotlinx.coroutines.*

fun main() {
    globalCoroutine()
//    Thread.sleep(2000)
    println()
    waitJob()
    println()
    coroutineScopeTest()
}

fun globalCoroutine() {
    GlobalScope.launch {
        delay(1000)
        println("global coroutine ${Thread.currentThread().name}")
    }
    GlobalScope.launch {
        delay(1000)
        println("global coroutine2 ${Thread.currentThread().name}")

        // GlobalScope 내의 coroutineScope는 해당 쓰레드(DefaultDispatcher-worker-1)에서 실행이 된다.
        coroutineScope {
            println("global coroutine3 ${Thread.currentThread().name}")
        }
    }
    println("hello coroutine ${Thread.currentThread().name}")
    val blocking = runBlocking {
        println("run blocking ${Thread.currentThread().name}")
        delay(2000)
        "complete"
    }
    println(blocking)
}

fun waitJob() = runBlocking {
    GlobalScope.launch {
        delay(2000L)
        println("World!")
    }
    launch {
        delay(1000L)
        println("World@@@@")
    }
    println("Hello,")
//    job.join() // wait until child coroutine completes
}

fun coroutineScopeTest() = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking ${Thread.currentThread().name}")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch ${Thread.currentThread().name}")
        }

        delay(100L)
        println("Task from coroutine scope ${Thread.currentThread().name}") // This line will be printed before the nested launch
    }

    println("Coroutine scope is over ${Thread.currentThread().name}") // This line is not printed until the nested launch completes
}