package com.jp.classes_objects

fun main() {
    val users = arrayListOf(User("ninck", 20), User("john", 30))
    showMembers(users)
    println()
    predicateInt()
}

fun showMembers(users: AllUser) {
    println(users)
}

// User의 리스트를 AllUser로 Type Aliasing
typealias AllUser = List<User>

// inner class를 TypeAlias로 보기 쉽게 관리
typealias AInner = A.InnerClass
class A {
    inner class InnerClass {
        fun showInner() {
        }
    }
}

typealias BInner = B.InnerClass
class B {
    inner class InnerClass {
        fun showInner() {
        }
    }
}

typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(42)

fun predicateInt() {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f)) // prints "true"

    val p: Predicate<Int> = { it > 0 }
    println(foo(p)) // prints "true"
    println(listOf(1, -2).filter(p)) // prints "[1]"
}