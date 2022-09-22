package com.codingame.thelabyrinth

class Kirk {

    lateinit var position: Position
    var state = State.EXPLORING
    val visitedPositions = mutableSetOf<Position>()
    var positionsToVisit = listOf<Position>()

}

enum class State {
    EXPLORING,
    GOING_TO_COMMAND_ROOM,
    ESCAPING
}
