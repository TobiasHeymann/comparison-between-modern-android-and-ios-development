package com.tobiasheymann.ep.model

import java.io.Serializable
import java.time.Instant

data class NewsMetadata(
    val uuid: String,
    val title: String,
    val timestamp: Instant,
    val image: String
) : Serializable {
    companion object {
        fun toObject(map: Map<String, Any>): NewsMetadata? {
            return if (
                map["uuid"] == null
                || map["title"] == null
                || map["timestamp"] == null
                || map["image"] == null
            )
                null
            else
                NewsMetadata(
                    map["uuid"]!! as String,
                    map["title"]!! as String,
                    Instant.parse(map["timestamp"]!! as String),
                    map["image"]!! as String
                )
        }
    }
}