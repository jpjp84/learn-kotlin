package com.jp.algorithm.problem.challenge

fun main() {
    val record = arrayOf(
            "Enter uid1234 Muzi",
            "Enter uid4567 Prodo",
            "Leave uid1234",
            "Enter uid1234 Prodo",
            "Change uid4567 Ryan"
    )
    println("Solution : ${OpenChatting().solution(record).toList()}")
}

class OpenChatting {

    fun solution(records: Array<String>): Array<String> {
        val nameMap = HashMap<String, String>()
        return records.map { record ->
            val splitRecord = record.split(" ")
            if (splitRecord.size == 3) {
                splitRecord[2].let { nameMap[splitRecord[1]] = splitRecord[2] }
            }
            Pair(splitRecord[0], splitRecord[1])
        }.mapNotNull {
            when (it.first) {
                "Enter" -> "${nameMap[it.second]}님이 들어왔습니다."
                "Leave" -> "${nameMap[it.second]}님이 나갔습니다."
                else -> null
            }
        }.toTypedArray()
    }

}
