package com.jp.classes_objects

fun main() {
    NestedAndInnerTest.Nested().foo()

    NestedAndInnerTest().Inner().foo()
}

class NestedAndInnerTest {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
        fun showBar() {
//            println(bar)
        }
    }

    inner class Inner {
        fun foo() = bar
        fun showBar() {
            println(bar)
        }
    }
}

