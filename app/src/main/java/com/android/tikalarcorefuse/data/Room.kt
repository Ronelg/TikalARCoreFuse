package com.android.tikalarcorefuse.data

data class Room(
    var id: String? = null,
    var name: String? = null,
    var numOfUser: Int? =0,
    var items: List<Items>? = null
)

data class Items(
    val x: Float,
    val y: Float,
    val z: Float
)