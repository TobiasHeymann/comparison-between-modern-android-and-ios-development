package com.tobiasheymann.ep.model

import java.io.Serializable

data class Content(
    val type: ContentType,
    val content: String
) : Serializable {
    companion object {
        fun toObject(map: Map<String, Any>): Content? {
            return if (
                map["type"] == null
                || map["content"] == null
            )
                null
            else
                Content(
                    ContentType(map["type"]!! as String)!!,
                    map["content"]!! as String
                )
        }
    }
}
