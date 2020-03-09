package com.jp.collections

fun main() {
    constructorCollectionTest(false)
    println()
    constructorCollectionTest(true)
    println()
    copyCollectionTest()
    println()
    invokeFunctionCollectionTest()
    println()
    zippingCollectionTest()
}

fun constructorCollectionTest(isTwoElement: Boolean) {
    val numbersMap = mutableMapOf<String, String>().apply {
        this["one"] = "1";
        if (isTwoElement) {
            this["two"] = "2";
        }
    }

    println(numbersMap)

    val doubled = List(3) { it * 2 } // or MutableList if you want to change its content later
    println(doubled)
}

fun copyCollectionTest() {
    val sourceList = mutableListOf(1, 2, 3)
    val shallowCopyList = sourceList
    val copyList = sourceList.toMutableList()
    val readOnlyCopyList = sourceList.toList()
    val copySet = sourceList.toMutableSet()
    sourceList.add(4)

    println(shallowCopyList)
    println(copyList)
    println(readOnlyCopyList)
    println(copySet)

}

fun invokeFunctionCollectionTest() {
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })

    val numbers2 = 0..10
    val numbers3 = numbers2.flatMap { number -> 1..number }
    println(numbers3)
    println(numbers2.map { "$it number!" })
}

fun zippingCollectionTest() {
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals)
    val zippedAnimal = colors zip animals
    val pairAnimal: Pair<String, String> = zippedAnimal[0]
    println(zippedAnimal.first())

    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals))
}