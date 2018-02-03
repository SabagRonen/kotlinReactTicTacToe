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
}

class Board : RComponent<BoardProp, BoardState>(), BoardContracts.View {
    var title: String = ""
    val userActions: BoardContracts.UserActions = BoardUserActions(this)

    override fun updateState(boardState: MyBoardState) {
        setState {
            when (boardState) {
                is BoardStateImpl -> {
                    squares = boardState.cells
                    title = boardState.title
                }
            }
        }
    }

    init {
        state.squares = MutableList(9){""}
        userActions.loadData()
    }

    override fun RBuilder.render() {
        div {
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
            userActions.cellClicked(index)
        }
    }
}

fun RBuilder.board() = child(Board::class){}