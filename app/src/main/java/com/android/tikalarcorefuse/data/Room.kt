package com.android.tikalarcorefuse.data

data class Room(
    var id: String? = null,
    var name: String? = null,
    var numOfUser: Int? =0,
    var items: List<Item>? = null,
    var users : List<String> = listOf(),
    var gameStart : Boolean = false
)

data class Item(
    var baloonId: String = "",
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f
)