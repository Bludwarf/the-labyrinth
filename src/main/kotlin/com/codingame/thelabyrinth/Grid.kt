package com.codingame.thelabyrinth

class Grid(val height: Int, val width: Int, private val buildEmptyCellAt: (Position) -> Cell) {
    private val cells = Array(height) { Array<Cell?>(width) { null } }

    private val cellsSequence
        get() = sequence {
            for (y in 0 until height) {
                for (x in 0 until width) {
                    yield(get(x, y))
                }
            }
        }

    operator fun set(x: Int, y: Int, cell: Cell) {
        cells[y][x] = cell
    }

    operator fun get(x: Int, y: Int): Cell = cells[y][x] ?: buildEmptyCellAt(Position(x, y))

    fun contains(predicate: (Cell) -> Boolean): Boolean = cellsSequence.any { cell -> predicate(cell) }

    fun firstCellOrNull(predicate: (Cell) -> Boolean): Cell? = cellsSequence.firstOrNull(predicate)

    fun positionOfOrNull(predicate: (Cell) -> Boolean): Position? {
        return firstCellOrNull(predicate)?.position
    }

}
