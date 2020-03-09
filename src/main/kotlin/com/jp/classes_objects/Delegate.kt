package com.jp.classes_objects

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }

    private var y : Int = 10

    // BaseImpl에는 정의 되어있지만 interface에 정의 되지 않은 메소드가 있다
    // Derived는 Base interface에 정의된 메소드들만 생성을 하기 때문에 아래 printY는 Derived에서 사용 할 수 없다
    fun printY() { print(y) }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}