package board

class BoardUserActions(private val view: BoardContracts.View) : BoardContracts.UserActions {
    var cells = List<String>(9){""}
    var xIsNext = true
    override fun loadData() {
        view.updateState(BoardStateImpl(cells = cells, title = "Next player: X"))

    }

    override fun cellClicked(index: Int) {
        if (calculateWinner(cells) != null || cells[index].isNotEmpty()) {
            return
        }

        xIsNext = !xIsNext
        val player = if (xIsNext) "X" else "O"
        cells = cells.mapIndexed { mapIndex, origin ->
            if (index != mapIndex) origin else player
        }
        val title = calculateWinner(cells)?.let {
            "Winner is $it"
        } ?: "Next player: $player"
        view.updateState(BoardStateImpl(cells = cells, title = title))
    }

    private fun calculateWinner(squares: List<String>): String? {
        val lines = arrayOf(
                intArrayOf(0, 1, 2),
                intArrayOf(3, 4, 5),
                intArrayOf(6, 7, 8),
                intArrayOf(0, 3, 6),
                intArrayOf(1, 4, 7),
                intArrayOf(2, 5, 8),
                intArrayOf(0, 4, 8),
                intArrayOf(2, 4, 6)
        )

        for (row in lines) {
            val a = row[0]
            val b = row[1]
            val c = row[2]
            if (!squares[a].isNullOrEmpty()) {
                if (squares[a].equals(squares[b])) {
                    if (squares[a].equals(squares[c])) {
                        return squares[a]
                    }
                }
            }
        }
        return null
    }

}