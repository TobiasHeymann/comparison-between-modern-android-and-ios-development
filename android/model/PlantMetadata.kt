package com.tobiasheymann.ep.model

import java.io.Serializable
import java.time.Instant

data class PlantMetadata(
    val uuid: String,
    val title: String,
    val subtitle: String,
    val timestamp: Instant,
    val image: String
) : Serializable {
    companion object {
        fun toObject(map: Map<String, Any>): PlantMetadata? {
            return if (
                map["uuid"] == null
                || map["title"] == null
                || map["subtitle"] == null
                || map["timestamp"] == null
                || map["image"] == null
            )
                null
            else
                PlantMetadata(
                    map["uuid"]!! as String,
                    map["title"]!! as String,
                    map["subtitle"]!! as String,
                    Instant.parse(map["timestamp"]!! as String),
                    map["image"]!! as String
                )
        }
    }
}
