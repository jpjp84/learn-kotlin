package com.jp.classes_objects

fun main() {
    println(Color.BLUE)
    Color.GREEN.showColor()
    println(Color.BLUE.colorName)
    println()
    ColorWithFormatter.BLUE.showColorName()
    ColorWithFormatter.BLUE.showColorRGB()
    println()
    enumTraveler()
}

// enumValues, enumValueOf를 활용하기
fun enumTraveler() {
    val colorEnum = enumValues<Color>()
    for (color: Color in colorEnum) {
        println(color.colorName)
    }

    val colorFormatEnum = enumValueOf<ColorWithFormatter>("BLUE")
    colorFormatEnum.showColorRGB()
}

// 초기화에는 primary constructor + enum class의 형태로 초기화를 한다
enum class Color(val rgb: Int, val colorName: String) {
    RED(0xFF0000, "RED!") {
        override fun showColor() {
            println("RED!")
        }
    },
    GREEN(0x00FF00, "GREEN!") {
        override fun showColor() {
            println("GREEN!")
        }
    },
    BLUE(0x0000FF, "BLUE!!") {
        override fun showColor() {
            println("$colorName")
        }
    };
    abstract fun showColor()
}

// interface를 사용하면 abstract를 선언 할 필요 없다
interface ColorFormat {
    val rgb: Int
    fun showColorName()
    fun showColorRGB() {
        println(rgb)
    }
}

enum class ColorWithFormatter(override val rgb: Int): ColorFormat {
    RED(0xFF0000) {
        override fun showColorName() {
            println("RED!!")
        }
    },
    GREEN(0x00FF00) {
        override fun showColorName() {
            println("GREEN!!")
        }
    },
    BLUE(0x0000FF) {
        override val rgb: Int
            get() = 0x0000FF
        override fun showColorName() {
            println("BLUE in fomatter")
        }
    }
}