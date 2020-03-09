package com.jp.classes_objects

interface Factory<T> {
    fun create(): T
}

class NetworkServiceWithCompanion {
//    companion object Network {
//        var ip: String = "127.0.0.1"
//        var port: Int = 8080
//    }

    companion object: Factory<NetworkServiceWithCompanion> {
        var ip: String = "127.0.0.1"
        var port: Int = 8080

        override fun create(): NetworkServiceWithCompanion {
            return NetworkServiceWithCompanion()
        }
    }

    var networkName: String = "test"

    fun getNetwork(): String {
        return ip
    }

    fun setNetwork(newIp: String) {
        ip = newIp
    }
}