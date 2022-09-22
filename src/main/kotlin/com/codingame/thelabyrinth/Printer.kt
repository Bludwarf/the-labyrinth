package com.codingame.thelabyrinth

import java.io.PrintStream

class Printer(private val printStream: PrintStream) {

    fun println(any: Any?) = printStream.println(any)
    private fun print(char: Char) = printStream.print(char)
    private fun println() = printStream.println()

    fun println(structure: Structure) = println(structure.grid)

    private fun println(grid: Grid) {
        for (y in 0 until grid.height) {
            for (x in 0 until grid.width) {
                print(grid[x, y])
            }
            println()
        }
    }

    private fun print(cell: Cell) {
        print(
            when (cell) {
                is Wall -> '#'
                is EmptySpace -> '.'
                is SpawnPosition -> 'T'
                is CommandRoom -> 'C'
                else -> '?'
            }
        )
    }

}
