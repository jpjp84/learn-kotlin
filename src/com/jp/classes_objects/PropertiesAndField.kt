package com.jp.classes_objects

//const val TEST_CONST_VARIABLE = "variable" //variable은 기본적으로 안된다.
//const val TEST_CONST_FUNCTION = propertiesTest() //runtime시 값을 셋팅하면 안된다

fun main() {
    propertiesTest();
}

fun propertiesTest() {
    var nullableValue: Int?
    val vale = 1

//    val isEmpty: Boolean
//        get() = false

    val propertiesCls = Properties()
    propertiesCls.name = "test!!"
    println(propertiesCls.name)

    println()
    propertiesCls.extra = hashMapOf("first" to "first", "sec" to "sec")
    println(propertiesCls.extra!!.keys)

    println()
    println(Properties.TEST_CONST)

    println()
    propertiesCls.setLateInit()
    println("late init : ${propertiesCls.lateInitVar}")
}

class Properties {
    var name: String? = null
        get() {
            println("get value in getter")
            if (field == null) {
                return "test default"
            }
            return field
        }
        set(value) {
            println("define value in setter")
            field = value
        }

    private var _table: Map<String, String>? = null
    var extra: Map<String, String>?
        get() {
            if (_table == null) {
                return HashMap()
            }
            return _table ?: throw AssertionError("not set name")
        }
        set(value) {
            _table = value
        }

    companion object {
        const val TEST_CONST = "TEST CONST VALUE"
    }

    lateinit var lateInitVar: String
    fun setLateInit() {
        lateInitVar = "init"
    }
}