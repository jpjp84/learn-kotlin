package com.jp.classes_objects

fun main() {
    objectExpressionTest()
}

fun objectExpressionTest() {
    var increaseTime = 0
    // interface를 object expression으로 표현을 하면 아래와 같다. 새로운 object를 바로 생성한다
    ListEvent().setEventAdapter(object : ListEventAdapter {
        override fun onClick() {
            println("onClick in ListEventAdapter")
            increaseTime++
        }
    })

    println()
    // class를 object expression으로 표현을 하면 아래와 같다. 새로운 class를 생성하고 기존 override한 function의 supertype을
    // 호출 할 수 있다
    ListEvent().setEventAdapter(object : ListEventAdapterImpl(3) {
        override fun onClick() {
            super.onClick()
            println("overrided onClick in ListEventAdapterImpl")
        }
    })

    //여러 supertype이 존재한다면 colon으로 구분을 해준다
    println()
    val ab: ListEventAdapter = object : ListItems(2), ListEventAdapter {
        override val y: Int = 15
        override fun onClick() {
            println("onClick ab $y")
        }
    }
    ab.onClick()

    println()
    val adhoc = object {
        var x = "adhoc x"
        var y = "adhoc y"
    }
    println("${adhoc.x} ${adhoc.y} ${adhoc.javaClass.kotlin.qualifiedName}")

    println()
    val listEvent = ListEvent()
    listEvent.printObjects()
}

interface ListEventAdapter {
    fun onClick()
}

open class ListItems(x: Int) {
    open val y: Int = x
}

open class ListEventAdapterImpl(size: Int) : ListEventAdapter {
    override fun onClick() {
        println("onClick in ListEventAdapterImpl")
    }
}

class ListEvent {
    fun setEventAdapter(event: ListEventAdapter) {
        event.onClick()
    }

    fun getPublicObject(): Any {
        return object {
            val x = 1
            val y = 2
        }
    }

    private fun getPrivateObject() = object {
        val x = 1
        val y = 2
    }

    fun printObjects() {
        println(getPublicObject())
        println(getPrivateObject().x)
    }
}