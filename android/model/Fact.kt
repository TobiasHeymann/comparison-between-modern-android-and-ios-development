package com.tobiasheymann.ep.model

import java.io.Serializable

data class Fact(
    val icon: FactIconType,
    val text: String
) : Serializable {
    companion object {
        fun toObject(map: Map<String, Any>): Fact? {
            return if (
                map["icon"] == null
                || map["text"] == null
            )
                null
            else
                Fact(
                    FactIconType(map["icon"]!! as String)!!,
                    map["text"]!! as String
                )
        }
    }
}