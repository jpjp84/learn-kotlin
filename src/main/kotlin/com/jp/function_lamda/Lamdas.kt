package com.jp.function_lamda

fun main() {
    highOrderFunctionTest()
    functionTypeTest()
    instantiatingFunctionType()
    lamdaExpressionTest()
    receiverFunctionTest()
}

fun highOrderFunctionTest() {
    val arrays = arrayListOf("test1", "test2", "test3", "test4", "test5")
    arrays.fold(mutableListOf()) { acc: MutableList<String>, i: String ->
        println("acc = $acc, i = $i, ")
        acc.add(i)
        // The last expression in a lambda is considered the return value:
        acc
    }
}

//fun <T, R> Collection<T>.fold(
//        initial: R,
//        combine: (acc: R, nextElement: T) -> R
//): R {
//    var accumulator: R = initial
//    for (element: T in this) {
//        accumulator = combine(accumulator, element)
//    }
//    return accumulator
//}

class LamdasA {
    fun showMessage(msg: String) {
        println("message : $msg")
    }
}

class LamdasB {
    object LamdaBObject {
        val data: String = "LamdaBObject"
    }
}

object LamdasC {
    val data: String = "test"
}

fun functionTypeTest() {
    println()
    // function type 정의
    val onClick: (LamdasA, LamdasB) -> String
    val onMove: LamdasB.(LamdasB.LamdaBObject) -> String

    val getOnClick: ((LamdasA, LamdasB) -> String) -> Unit

    // instantiating function type
    val showMessage: LamdasA.(String) -> Unit = { msg ->
        this.showMessage(msg)
    }
    val twoParamShowMessage: (LamdasA, String) -> Unit = showMessage

    fun lamdaPrintln(f: (LamdasA, String) -> Unit) {
        f(LamdasA(), "its Lamda A Message!")
    }

    lamdaPrintln(showMessage)
}

fun instantiatingFunctionType() {
    println()
    val stringPlus: (String, String) -> String = String::plus
    val intPlus1: (Int, Int) -> Int = Int::plus
    val intPlus2: Int.(Int) -> Int = Int::plus

    println(stringPlus("test", " message"))
    println(intPlus1.invoke(4, 5))
    println(1.intPlus2(4))
}

fun lamdaExpressionTest() {
    println()
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val sum2 = { x: Int, y: Int -> x + y }
    //it의 사용
//    val sumWith4: (Int) -> Int = { x: Int -> x + 4 }
    val sumWith4: (Int) -> Int = { it + 4 }

    val ints = arrayListOf(1, 2, 3, 4, 5)
    val fileteredInt = ints.filter {
        val result = it > 1
        return@filter result
    }

    ints.filter(fun(item) = item > 0)

    println(fileteredInt)
}

fun receiverFunctionTest() {
    html {
        // lambda with receiver begins here
        body()   // calling a method on the receiver object
    }
}

class HTML {
    fun body() {

    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}
