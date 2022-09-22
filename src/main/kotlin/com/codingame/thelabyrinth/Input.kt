package com.codingame.thelabyrinth

import java.util.*

@JvmInline
value class Input(private val input: Scanner) {
    fun next(): String = input.next()
    fun nextInt(): Int = input.nextInt()

    fun nextStructure(structureHeight: Int, structureWidth: Int): Structure {
        val grid = Grid(structureHeight, structureWidth) { position ->
            NotScannedCell(position)
        }
        for (y in 0 until structureHeight) {
            val row = input.next() // C of the characters in '#.TC?' (i.e. one line of the ASCII maze).
            row.mapIndexed { x, char ->
                val position = Position(x, y)
                when (char) {
                    '#' -> Wall(position)
                    '.' -> EmptySpace(position)
                    'T' -> SpawnPosition(position)
                    'C' -> CommandRoom(position)
                    else -> null
                }.takeIf { it != null }?.let { cell ->
                    grid[x, y] = cell
                }
            }
        }
        return Structure(grid)
    }

    fun nextPosition(): Position {
        val y = input.nextInt() // row where Rick is located.
        val x = input.nextInt() // column where Rick is located.
        return Position(x, y)
    }

}
