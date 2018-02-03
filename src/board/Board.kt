package board

import square.square
import state.status
import kotlinx.html.DIV
import react.*
import react.dom.RDOMBuilder
import react.dom.div

interface BoardProp : RProps
interface BoardState : RState {
    var squares: List<String>
    var xIsNext: Boolean
}

class Board : RComponent<BoardProp, BoardState>() {
    init {
        state.squares = MutableList(9){""}
        state.xIsNext = true
    }
    override fun RBuilder.render() {
        div {
            val title = calculateWinner(state.squares)?.let {
                "Winner is $it"
            } ?: "Next player: ${if (state.xIsNext) "X" else "O"}"
            status(title)
            div {
                renderSquare(0)
                renderSquare(1)
                renderSquare(2)
            }

            div {
                renderSquare(3)
                renderSquare(4)
                renderSquare(5)
            }

            div {
                renderSquare(6)
                renderSquare(7)
                renderSquare(8)
            }

        }
    }

    private fun RDOMBuilder<DIV>.renderSquare(index: Int) {
        square(state.squares[index]) {
            handleClick(index)
        }
    }

    private fun handleClick(index: Int) {
        if (calculateWinner(state.squares) != null || state.squares[index].isNotEmpty()) {
            return;
        }
        setState {
            squares = squares.mapIndexed { mapIndex, origin ->
                if (mapIndex != index) {
                    origin
                } else if (xIsNext) {
                    "X"
                } else {
                    "O"
                }
            }
            xIsNext = !xIsNext
        }
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

fun RBuilder.board() = child(Board::class){}