package com.jp.classes_objects

import kotlin.reflect.KProperty

fun main() {
    var p: DelegateProperty by DelegatePropertyImpl()
    p.showDelegateProperty()
    p = object : DelegateProperty {
        override fun showDelegateProperty() {
            println("showDelegateProperty3")
        }
    }

    var o: String

}


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