package com.codingame.thelabyrinth

data class Structure(val grid: Grid) {

    operator fun get(x: Int, y: Int): Cell = grid[x, y]

    private fun neighborsOf(position: Position): Sequence<Cell> {
        val (x, y) = position
        return sequence {
            if (x > 0) {
                yield(get(x - 1, y))
            }
            if (x < grid.width - 1) {
                yield(get(x + 1, y))
            }
            if (y > 0) {
                yield(get(x, y - 1))
            }
            if (y < grid.height - 1) {
                yield(get(x, y + 1))
            }
        }
    }

    fun cellAt(position: Position): Cell {
        val (x, y) = position
        return this[x, y]
    }

    fun emptyCellsAround(position: Position): Sequence<Cell> = neighborsOf(position).filter { it is EmptySpace }

    val spawnPosition: Position by lazy {
        grid.positionOfOrNull { it is SpawnPosition } ?: throw Throwable("Cannot find spawn position.")
    }
    val commandRoomIsVisible: Boolean by lazy {
        commandRoomPosition != null
    }
    val commandRoomPosition: Position? by lazy {
        grid.positionOfOrNull { it is CommandRoom }
    }
}
