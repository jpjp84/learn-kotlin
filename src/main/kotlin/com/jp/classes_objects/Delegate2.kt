package com.jp.classes_objects

interface LoginBySDK {
    fun login()
    fun logout()
}

class Kakaotalk : LoginBySDK {
    override fun login() {
        println("login by kakaotalk sdk")
    }

    override fun logout() {
        println("logout by kakaotalk sdk")
    }
}

class Facebook : LoginBySDK {
    override fun login() {
        println("login by facebook sdk")
    }

    override fun logout() {
        println("logout by facebook sdk")
    }
}

class LoginDelegator(impl: LoginBySDK) : LoginBySDK by impl {
    fun doLogin() {
        login()
    }
}

fun main(args: Array<String>) {
    val loginDelegator = LoginDelegator(Kakaotalk())
//    val loginDelegator = LoginDelegator(Facebook())

    loginDelegator.doLogin()
}