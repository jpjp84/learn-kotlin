package com.jp.classes_objects

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

fun main() {
    delegatePropertyTest()
    println()
    lazyPropertyTest()
    println()
    observerPropertyTest()
    println()
    storingProperiesTest()
}

fun delegatePropertyTest() {

    var p: DelegateProperty by DelegatePropertyImpl()
    p.showDelegateProperty()
    p = object : DelegateProperty {
        override fun showDelegateProperty() {
            println("showDelegateProperty3")
        }
    }

    var o: String
}

//DelegateProperty
class DelegatePropertyImpl : DelegateProperty {
    override fun showDelegateProperty() {
        println("showDelegateProperty")
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): DelegateProperty {
        return object : DelegateProperty {
            override fun showDelegateProperty() {
                println("showDelegateProperty1")
            }
        }
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: DelegateProperty) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

interface DelegateProperty {
    fun showDelegateProperty()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

fun lazyPropertyTest() {
    // lazy의 범위는 객체를 생성할 때 해당 객체 내로 제한된다
    val lazyPropertyClass = LazyValueClass()
    println("lazy: ${lazyPropertyClass.lazyValue}")
    lazyPropertyClass.lazyValue
    println("lazy 1: ${lazyPropertyClass.lazyValue}")

    println("lazy 2: ${LazyValueClass().lazyValue}")
}

class LazyValueClass {
    //    val lazyValue: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

//    var lazeVariable: String by lazy {
//        "Hi"
//    }
}

fun observerPropertyTest() {
    val user = ObserverUserTest()
    user.name = "first"
    user.name = "second"

    user.name2 = "second"
    println("${user.name2}")
}

class ObserverUserTest {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$prop : $old -> $new")
    }
    var name2: String by Delegates.vetoable("<no name>") { property, oldValue, newValue ->
        println("$property : $oldValue -> $newValue")
        true
    }
}

// Storing Properties in a Map

class UserMap(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun storingProperiesTest() {
    val userMap = UserMap(mapOf(
            "name" to "john"
    ))

    println(userMap.toString())
}
