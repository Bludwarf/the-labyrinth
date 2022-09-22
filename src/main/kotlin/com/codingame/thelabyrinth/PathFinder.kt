package com.codingame.thelabyrinth

import Path
import java.util.*

class PathFinder {
    fun findPath(a: Position, b: Position): Path {
        val frontier = PriorityQueue<Position>()
        // TODO https://www.redblobgames.com/pathfinding/a-star/introduction.html#astar
        return Path(listOf(a, b))
    }
}
