package com.jp.coroutines

import kotlinx.coroutines.*

fun main() {
    dispatcherTest()
    println()
    unconfinedDispatcherTest()
//    println()
//    inheriteContextTest()
    println()
    waitParentTest()

    println()
    threadLocalDataTest()
}

fun dispatcherTest() = runBlocking {
    launch {
        println("not option thread is ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Dispatchers.Default thread is ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Dispatchers.Unconfined thread is ${Thread.currentThread().name}")
    }

    launch(newSingleThreadContext("jp-thread")) {
        println("Dispatchers.Unconfined thread is ${Thread.currentThread().name}")
    }

// android 전용 coroutine context
//    launch(Dispatchers.Main) {
//        println("Dispatchers.Main thread is ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.IO) {
//        println("Dispatchers.IO thread is ${Thread.currentThread().name}")
//    }
}

fun unconfinedDispatcherTest() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        // not confined -- will work with main thread
        println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
//        delay(500)
        println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
    }
    launch {
        // context of the parent, main runBlocking coroutine
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

fun inheriteContextTest() = runBlocking {
    val request = launch {
        // it spawns two other jobs, one with GlobalScope
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
//    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}

fun waitParentTest() = runBlocking {
    val request = launch {
        repeat(3) { i -> // launch a few children jobs
            launch  {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
//    request.join() // wait for completion of the request, including all its children
    println("Now processing of the request is complete")
}

val threadLocal = ThreadLocal<String?>()

fun threadLocalDataTest() = runBlocking<Unit> {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    val runblockingContext = coroutineContext
    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch") + handler) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        // thread local 수정
        threadLocal.set("launch2")
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")

        // 새로운 single thread에서는 변경한 launch2가 아닌 기존의 launch라는 값으로 접근이 된다
        launch(newSingleThreadContext("jp-thread")) {
            println("new single thread, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        launch(newSingleThreadContext("jp-thread2") + threadLocal.asContextElement(value = "launch3")) {
            println("new single thread and modify value, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        withContext(runblockingContext + threadLocal.asContextElement(value = "launch4")) {
            println("new parent coroutine context, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
//        throw AssertionError()
    }

    val job2 = GlobalScope.launch(handler) {
        throw AssertionError()
    }
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
    }
    joinAll(job2, deferred.await())
    job.join()
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}