package com.android.tikalarcorefuse.data

data class Room(
    var id: String? = null,
    var name: String = "",
    var numOfUser: Int = 0,
    var items: List<Item> = ArrayList()
)

data class Item(
    var baloonId: String = "",
    var x: Float = 0f,
    var y: Float = 0f,
    var z: Float = 0f
)