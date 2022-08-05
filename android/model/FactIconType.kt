package com.tobiasheymann.ep.model

enum class FactIconType(val rawValue: String) {
    HEIGHT("height"),
    WIDTH("width"),
    THERMOSTAT("thermostat"),
    TRAVEL_EXPLORE("travel_explore");

    companion object {
        operator fun invoke(rawValue: String) = values().firstOrNull { it.rawValue == rawValue }
    }
}