package com.tobiasheymann.ep.model

import java.io.Serializable

data class News(
    val metadata: NewsMetadata,
    val content: List<Content>
) : Serializable {
    companion object {
        const val COLLECTION_NAME = "news"

        fun toObject(map: Map<String, Any>): News? {
            if (map["metadata"] == null || map["content"] == null) {
                return null
            }

            val metadata = NewsMetadata.toObject(map["metadata"]!! as Map<String, Any>)
            val content = mutableListOf<Content>()
            for (item in map["content"]!! as List<Map<String, Any>>) {
                Content.toObject(item)?.let {
                    content.add(it)
                }
            }

            return if (metadata == null) {
                null
            } else {
                News(metadata!!, content)
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