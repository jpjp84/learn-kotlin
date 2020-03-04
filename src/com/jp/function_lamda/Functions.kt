package com.jp.function_lamda

fun main() {
    functionArgumentTest()
    println()
    infixFunctionTest()
}

fun functionArgumentTest() {
    val functions = Functions()
    functions.defaultArgumentTest(arrayListOf("test1", "test2"))
    val extendedFunctions = ExtendedFunctions()
    extendedFunctions.defaultArgumentTest(arrayListOf("test3", "test4"))

    println()
    // named argument를 사용하여 default argument를 생략
    functions.namedArgumentTest(baz = 3)

    println()
    // last lamda argument, lamda argument
    functions.foo(baz = 1) { println("qux function") }
    functions.foo2(baz = 2, qux = { println("qux function") })

    println()
    //spread function
    val arrayArgument = arrayOf("test1", "test2")
    functions.spreadArgument(*arrayArgument)
    println(arrayArgument)

    functions.spreadArgument("test1", "test2")
}

open class Functions {
    open fun defaultArgumentTest(array: MutableList<String>, size: Int = array.size, offset: Int = 3) {
        println("$array, $size")
    }

    fun namedArgumentTest(bar: Int = 0, baz: Int) {
        println("$bar, $baz")
    }

    fun foo(bar: Int = 0, baz: Int, qux: () -> Unit) {
        println("$bar, $baz")
        qux()
    }

    fun foo2(bar: Int = 0, qux: () -> Unit, baz: Int) {
        println("$bar, $baz")
        qux()
    }

    fun spreadArgument(vararg strings: String) {
        for (string: String in strings) {
            print("$string ")
        }
        println(strings)
    }

    infix fun show(msg: String) {
        println(msg)
    }

    tailrec fun addPlus1(value: Int): Int {
        if (value == -1) {
            return value
        }

        print("$value ")
        return addPlus1(value + 1)
    }
}

class ExtendedFunctions : Functions() {
    override fun defaultArgumentTest(array: MutableList<String>, size: Int, offset: Int) {
        super.defaultArgumentTest(array, size, offset)
    }
}

fun infixFunctionTest() {
    val functions = Functions()
    functions show "test"
}