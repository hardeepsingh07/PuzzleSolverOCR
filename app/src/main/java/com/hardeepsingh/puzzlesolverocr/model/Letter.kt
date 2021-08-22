import kotlin.random.Random

const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"

val colors = listOf(ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN)
fun randomColor() = colors.random(Random(System.currentTimeMillis()))

data class Dimension(val row: Int, val col: Int, var tempDimension: Pair<Int, Int> = Pair(0, 0)) {

    fun size() = row.plus(col).minus(1)
    fun createTempDimension(row: Int, col: Int) {
        tempDimension = Pair(row, col)
    }

    fun validTempDimensions() = tempDimension.first < col && tempDimension.second < row
    fun incrementTempDimensions() {
        tempDimension = Pair(tempDimension.first + 1, tempDimension.second + 1)
    }
}

data class Letter(val element: Char, var color: String = ANSI_RESET) {
    fun reset() { color = ANSI_RESET }
    override fun toString(): String = color + element + ANSI_RESET
}

fun Char.toLetter() = Letter(this, ANSI_RESET)