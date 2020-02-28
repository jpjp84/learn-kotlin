package com.jp.classes_objects

fun main() {
    objectDeclarationTest()

    singleonTest()

    companionTest()
}

fun objectDeclarationTest() {
    Object2A().showSingletonObject()
    Object2B().showSingletonObject()
}

fun singleonTest() {
    println()
    val service1 = NetworkService()
    println("${service1.getNetwork()}, ${service1.networkName}")
    service1.setNetwork("0.0.0.0") // object Network의 ip주소를 변경 -> singleton으로 작동!
    service1.networkName = "jp" // class의 networkName을 변경 -> singleton으로 작동안함
    val service2 = NetworkService()
    println("${service2.getNetwork()}, ${service2.networkName}")
    println("${service1 == service2}, ${service1 === service2}")
    println(NetworkService.Network.ip)
}

fun companionTest() {
    println()
    val service1 = NetworkServiceWithCompanion()
    println("${service1.getNetwork()}, ${service1.networkName}")
    service1.setNetwork("0.0.0.0") // object Network의 ip주소를 변경 -> singleton으로 작동!
    service1.networkName = "jp" // class의 networkName을 변경 -> singleton으로 작동안함
    val service2 = NetworkServiceWithCompanion()
    println("${service2.getNetwork()}, ${service2.networkName}")
    println("${service1 == service2}, ${service1 === service2}")
    println(NetworkServiceWithCompanion.ip)
    println(NetworkServiceWithCompanion.Companion.ip)
}

class Object2A {
    fun showSingletonObject() {
        println(SingletonObjects.x)
        SingletonObjects.x = 10
    }
}

class Object2B {
    fun showSingletonObject() {
        println(SingletonObjects.x)
    }
}