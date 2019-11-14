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
    val id: String = "",
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f
)