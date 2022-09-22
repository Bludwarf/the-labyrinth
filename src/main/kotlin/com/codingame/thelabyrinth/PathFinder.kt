package com.codingame.thelabyrinth

import Path
import debug
import java.util.*

typealias PositionWithCost = Pair<Position, Int>

class PathFinder(private val structure: Structure) {
    fun findPath(start: Position, goal: Position): Path {
        debug("Find path to $start to $goal")

        val cameFrom = buildCameFromMapWithAStar(start, goal)
        return buildPathFromCameFromMap(start, goal, cameFrom).also {
            debug("Path :")
            debug(it)
        }
    }

    /**
     * Source : https://www.redblobgames.com/pathfinding/a-star/introduction.html#astar
     */
    private fun buildCameFromMapWithAStar(start: Position, goal: Position): Map<Position, Position?> {
        val frontier = PriorityQueue<PositionWithCost>(Comparator.comparing { it.second })
        frontier += start to 0

        val cameFrom = mutableMapOf<Position, Position?>()
        cameFrom[start] = null

        val costSoFar = mutableMapOf<Position, Int>()
        costSoFar[start] = 0

        while (frontier.isNotEmpty()) {
            val (current) = frontier.remove()
            if (current == goal) {
                break;
            }

            val neighbors = structure.neighborsOf(current)
                .filter { it !is Wall }
                .filter { it !is NotScannedCell }
                .map { it.position }
            for (next in neighbors) {
                val newCost = (costSoFar[current] ?: 0) + cost(current, next)
                if (costSoFar[next] == null || newCost < (costSoFar[next] ?: 0)) {
                    costSoFar[next] = newCost
                    val priority = newCost + heuristic(goal, next)
                    frontier.add(next to priority)
                    cameFrom[next] = current
                }
            }
        }

        debug("came_from")
        debug(cameFrom)
        debug("cost_so_far")
        debug(costSoFar)

        return cameFrom.toMap()
    }

    /**
     * Source : https://www.redblobgames.com/pathfinding/a-star/introduction.html#breadth-first-search
     */
    private fun buildPathFromCameFromMap(start: Position, goal: Position, cameFrom: Map<Position, Position?>): Path {
        var current = goal
        val positions = mutableListOf<Position>()
        while (current != start) {
            positions += current
            current = cameFrom[current] ?: throw Throwable("Don't know where we can from $current")
        }
        positions += start
        return Path(positions.reversed())
    }

    private fun cost(current: Position, next: Position): Int = 1

    private fun heuristic(goal: Position, next: Position): Int = goal.squareDistanceWith(next)
}
