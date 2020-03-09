package com.jp.classes_objects

interface MyInterface {
    val name: String

    val age: Int
        get() = 15
//        set(value) {
//            println(value)
//        }

    val nickname: String
//        get() {
//            if (field.isEmpty()) {
//                return "non-nickname"
//            }
//            return field
//        }

    fun getName()

    fun isAdult(): Boolean {
        return false
    }
}

interface YourInterface {
    val name: String

    val age: Int
        get() = 20
//        set(value) {
//            println(value)
//        }

    fun getName()

    fun isAdult(): Boolean {
        return true
    }
}

class MyClass : MyInterface, YourInterface {
    override val name: String
        get() = "non-name"

    override val age: Int
        get() = 25

    override val nickname: String
        get() = "non-nickname"

    override fun getName() {
        println("$age $name")
        println("${super<MyInterface>.age}, ${super<YourInterface>.age}")
    }

    override fun isAdult(): Boolean {
        println("${super<MyInterface>.isAdult()}")
        println("${super<YourInterface>.isAdult()}")
        return false
    }
}


fun main() {
    val myClass = MyClass()
    myClass.getName()
    myClass.isAdult()
}