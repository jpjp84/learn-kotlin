package com.jp.classes_objects

fun main() {

}

//data class Login(val type: String)
sealed class Login(open val type: String)

interface LoginType {}

data class KakaoTalk(val id: String, val password: String, override val type: String): Login(type)

data class Line(val id: String, val password: String): LoginType

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
}