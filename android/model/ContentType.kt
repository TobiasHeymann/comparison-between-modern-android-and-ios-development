package com.tobiasheymann.ep.model

enum class ContentType(val rawValue: String) {
    HEADER("header"),
    TEXT("text"),
    IMAGE("image"),
    MAP("map");

    companion object {
        operator fun invoke(rawValue: String) = values().firstOrNull { it.rawValue == rawValue }
    }
}