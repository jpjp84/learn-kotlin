package com.jp.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

fun main() {
    channelTest()
    println()
    produceTest()
    println()
    fanoutTest()
    println()
    bufferedChannelTest()
}

fun channelTest() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..15) {
            if (channel.isClosedForSend) {
                println("isClosedForSend")
                break
            }

            println("channel send $x")
            channel.send(x * x)
        }

        println("channel end")
    }

    repeat(5) {
        delay(2000)
        println(channel.receive())
    }

    for (ch in channel) {
        println("channel is $ch")
    }
    println("Done!")
}

fun CoroutineScope.numbering() = produce {
    for (x in 1..15) {
        send(x)
    }
}

fun CoroutineScope.produceSquares(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) {
        send(x * x)
    }
}

fun produceTest() = runBlocking {
    val numbers = numbering()
    val calculator = produceSquares(numbers)
// produce 역시 당연하게도 iteration을 돌 수 있다.
//    for (result in calculator) {
//        println("calculator : $result")
//    }

    repeat(5) {
        println("calculator : ${calculator.receive()}")
    }
    coroutineContext.cancelChildren()
}

fun fanoutTest() = runBlocking {
    val numbers = numbering()
    receiverCoroutine(1, numbers)
    receiverCoroutine(2, numbers)

    delay(1000)
    numbers.cancel()
}

fun CoroutineScope.receiverCoroutine(id: Int, channel: ReceiveChannel<Int>) = launch {
    try {
        for (y in channel) {

            if (y == 2) {
                throw RuntimeException()
            }
            println("coroutine($id) thread(${Thread.currentThread().name}) : $y")
        }
    } catch (e: Exception) {
        println("Exception!!")
    }

    //consumeEach를 사용하는 경우 Exception이 발생하면 channel이 close되어 버린다. consumeEach 내부에 try~catch를
    // 하거나 for-loop를 사용하자
//    try {
//        channel.consumeEach {
//            if (it == 2) {
//                throw RuntimeException()
//            }
//            println("coroutine($id) thread(${Thread.currentThread().name}) : $it")
//        }
//    } catch (e: Exception) {
//        println("Exception!!")
//    }
}

fun bufferedChannelTest() = runBlocking<Unit> {
    val channel = Channel<Int>(4) // create buffered channel
    val sender = launch {
        repeat(10) {
            println("Sending $it")
            channel.send(it)
        }
    }

    delay(1000)
    println("receiver : ${channel.receive()}")
    sender.cancel() // cancel sender coroutine
}