package com.android.tikalarcorefuse.data

data class Room(
    var id: String? = null,
    var name: String? = null,
    var numOfUser: Int? =0,
    var items: List<Item>? = null
)

data class Item(
    var x: Float,
    var y: Float,
    var z: Float
)