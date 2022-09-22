package com.codingame.thelabyrinth

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Position(val x: Int, val y: Int) {
    override fun toString(): String {
        return "$x $y"
    }

    fun isNextTo(position: Position): Boolean {
        return abs(this.x - position.x) <= 1 && abs(this.y - position.y) <= 1
    }

    fun straightDistanceWith(position: Position): Double {
        return sqrt(abs(position.x - x).toDouble().pow(2) + abs(position.y - y).toDouble().pow(2))
    }

    fun squareDistanceWith(position: Position): Int {
        return abs(position.x - x) + abs(position.y - y)
    }

    fun directionOf(position: Position): Direction {
        val (x1, y1) = position
        return if (x1 < x) {
            Direction.LEFT
        } else if (x1 > x) {
            Direction.RIGHT
        } else if (y1 < y) {
            Direction.UP
        } else if (y1 > y) {
            Direction.DOWN
        } else {
            TODO("Direction not found")
        }
    }

    /*

    val adjacentPositions: Set<Position> by lazy {
        val theoricalAdjacentPositions = listOf(
            Position(x, y - 1),
            Position(x - 1, y),
            Position(x + 1, y),
            Position(x, y + 1),
        )

        val validAdjacentPositions = mutableSetOf<Position>()
        for (adjacentPosition in theoricalAdjacentPositions) {
            if (x >= 0 && y >= 0 && x <= MAX_X && y <= MAX_Y) {
                validAdjacentPositions += adjacentPosition
            }
        }

        validAdjacentPositions.toSet()
    }

    companion object {
        const val MAX_X = 10
        const val MAX_Y = 6
    }
     */

}
