package com.tobiasheymann.ep.model

import java.io.Serializable

data class Plant(
    val metadata: PlantMetadata,
    val facts: List<Fact>,
    val content: List<Content>
) : Serializable {
    companion object {
        const val COLLECTION_NAME = "plants"

        fun toObject(map: Map<String, Any>): Plant? {
            if (map["metadata"] == null || map["content"] == null) {
                return null
            }

            val metadata = PlantMetadata.toObject(map["metadata"]!! as Map<String, Any>)
            val facts = mutableListOf<Fact>()
            for (item in map["facts"]!! as List<Map<String, Any>>) {
                Fact.toObject(item)?.let {
                    facts.add(it)
                }
            }
            val content = mutableListOf<Content>()
            for (item in map["content"]!! as List<Map<String, Any>>) {
                Content.toObject(item)?.let {
                    content.add(it)
                }
            }

            return if (metadata == null) {
                null
            } else {
                Plant(metadata!!, facts, content)
            }
        }
    }

    val firstText: String
        get() {
            for (c in content) {
                if (c.type === ContentType.TEXT) {
                    return c.content
                }
            }
            return ""
        }
}