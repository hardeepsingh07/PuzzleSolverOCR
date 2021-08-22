class Solver(private val dimensions: Dimension, private var puzzle: String, val words: List<String>) {

    val rows: List<List<Letter>> = generateRows()
    val cols: List<List<Letter>> = generateColumns()
    val startDiagonals: List<List<Letter>> = generateStartDiagonals()
    val endDiagonals: List<List<Letter>> = generateEndDiagonals()

    private fun generateRows() =
        mutableListOf<List<Letter>>().apply {
            puzzle.split("(?<=\\G.{${dimensions.row}})".toRegex()).dropLast(1).forEachIndexed { index, s ->
                add(s.toList().map { it.toLetter() })
            }
        }

    private fun generateColumns() =
        mutableListOf<List<Letter>>().apply {
            (0 until dimensions.row).forEach { add(rows.map { innerList -> innerList[it] }) }
        }

    private fun generateStartDiagonals() =
        mutableListOf<List<Letter>>().apply {
            (0 until dimensions.size()).forEach { index ->
                val list = mutableListOf<Letter?>()
                (0..index).forEach {
                    if (index - it < dimensions.col && it < dimensions.row) list.add(rows[index - it][it])
                }
                add(list.filterNotNull())
            }
        }

    private fun generateEndDiagonals() =
        mutableListOf<List<Letter>>().apply {
            (rows.size - 1 downTo 1).forEach { index -> add(getDiagonal(index, 0)) }
            rows.forEachIndexed { index, _ -> add(getDiagonal(0, index)) }
        }

    private fun getDiagonal(row: Int, col: Int): List<Letter> =
        mutableListOf<Letter?>().apply {
            dimensions.createTempDimension(row, col)
            while (dimensions.validTempDimensions()) {
                add(rows[dimensions.tempDimension.first][dimensions.tempDimension.second])
                dimensions.incrementTempDimensions()
            }
        }.filterNotNull()


    fun printPuzzleInMatrix() {
        rows.forEach { println(it) }
    }

    fun computeSolution() {
        words.forEach { word ->
            listOf(
                rows.filter { it.size >= word.length },
                cols.filter { it.size >= word.length },
                startDiagonals.filter { it.size >= word.length },
                endDiagonals.filter { it.size >= word.length }
            ).forEach {
                it.forEach { if (it.highlightIfWordFound(word, false)) return@forEach }
            }
        }
    }

    private fun List<Letter>.highlightIfWordFound(word: String, isDiagonal: Boolean = false): Boolean {
        val listString = joinToString("") { it.element.toString() }
        return when {
            listString.contains(word) -> listString.indexOf(word, ignoreCase = true)
            listString.contains(word.reversed()) -> listString.indexOf(word.reversed(), ignoreCase = true)
            else -> -1
        }.takeIf { it != -1 }?.let {
            (it until word.length + if (isDiagonal) 0 else 1).forEach { get(it).apply { color = randomColor() } }
            true
        } ?: false
    }

    fun reset() {
        rows.forEach { it.forEach { it.reset() } }
    }
}