import com.codingame.thelabyrinth.Input
import com.codingame.thelabyrinth.Kirk
import com.codingame.thelabyrinth.DirectionResolver
import com.codingame.thelabyrinth.Printer
import java.util.*

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
fun main() {
    val input = Input(Scanner(System.`in`))
    val structureHeight = input.nextInt() // number of rows.
    val structureWidth = input.nextInt() // number of columns.
    val countdown = input.nextInt() // number of rounds between the time the alarm countdown is activated and the time the alarm goes off.
    val debugPrinter = Printer(System.err)
    val kirk = Kirk()

    // game loop
    while (true) {
        kirk.position = input.nextPosition()
        debugPrinter.println(kirk.state)

        val structure = input.nextStructure(structureHeight, structureWidth)
        debugPrinter.println(structure)

        val directionResolver = DirectionResolver(structure, kirk)

        println(directionResolver.nextDirection) // Rick's next move (UP DOWN LEFT or RIGHT).
    }
}

fun debug(string: String) = System.err.println(string)
fun debug(x: Any) = System.err.println(x)
