package com.jp.classes_objects

class NetworkService {
    object Network {
        var ip: String = "127.0.0.1"
        var port: Int = 8080
    }
    var networkName: String = "test"

    fun getNetwork(): String {
        return Network.ip
    }

    fun setNetwork(newIp: String) {
        Network.ip = newIp
    }
}