package board

sealed class MyBoardState
data class BoardStateImpl(val title: String, val cells: List<String>) : MyBoardState()

interface BoardContracts {
    interface View {
        fun updateState(boardState: MyBoardState)
    }
    interface UserActions {
        fun loadData()
        fun cellClicked(index: Int)
    }
}

