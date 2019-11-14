package com.android.tikalarcorefuse.data

data class Room(
    val name: String? = null,
    val numOfUser: Int? =0,
    val items: List<Items>? = null
)

data class Items(
    val x: Float,
    val y: Float,
    val z: Float
)