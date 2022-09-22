package com.codingame.thelabyrinth

import debug

class DirectionResolver(private val structure: Structure, private val kirk: Kirk) {

    private val pathFinder = PathFinder(structure)

    val nextDirection: Direction by lazy {
        when (kirk.state) {
            State.EXPLORING -> if (structure.commandRoomIsVisible) {
                if (kirk.positionsToVisit.isEmpty()) {
                    goToCommandRoom()
                } else {
                    debug("Command room found. Exploring all structure before...")
                    kirk.positionsToVisit -= structure.commandRoomPosition!!
                    explore()
                }
            } else {
                explore()
            }

            State.GOING_TO_COMMAND_ROOM -> if (kirkIsInCommandRoom) {
                escape()
            } else {
                goToCommandRoom()
            }

            State.ESCAPING -> escape()
        }
    }

    private val kirkCell: Cell by lazy {
        structure.cellAt(kirk.position)
    }

    private val kirkIsInCommandRoom by lazy { kirkCell is CommandRoom }

    private fun explore(): Direction {
        kirk.state = State.EXPLORING
        kirk.visitedPositions += kirk.position
        kirk.positionsToVisit = (structure.emptyCellsAround(kirk.position)
            .map { it.position }
            .toList() + kirk.positionsToVisit)
            .filter { !kirk.visitedPositions.contains(it) }
        debug("kirk.positionsToVisit")
        debug(kirk.positionsToVisit)
        val nextPositionToVisit = kirk.positionsToVisit.firstOrNull()
            ?: return if (structure.commandRoomIsVisible) {
                goToCommandRoom()
            } else {
                throw Throwable("Nothing to explore !")
            }
        return goTo(nextPositionToVisit)
    }

    private fun goTo(position: Position): Direction {
        val path = pathFinder.findPath(kirk.position, position)
        return kirk.position.directionOf(path.positions[1])
    }

    private fun goToCommandRoom(): Direction {
        kirk.state = State.GOING_TO_COMMAND_ROOM
        val commandRoomPosition = structure.commandRoomPosition
        return if (commandRoomPosition != null) {
            goTo(commandRoomPosition)
        } else {
            System.err.println("Cannot go to command room for its position in structure is unknown. Exploring instead...")
            explore()
        }
    }

    private fun escape(): Direction {
        kirk.state = State.ESCAPING
        return goTo(structure.spawnPosition)
    }

}
