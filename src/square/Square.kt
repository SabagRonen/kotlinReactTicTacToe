package square

import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button

class Square : RComponent<SquareProps, SquareState>() {
    override fun RBuilder.render() {
        button {
            + props.value
            attrs {
                onClickFunction = {
                    props.onClick()
                }
            }
        }
    }
}

interface SquareProps : RProps {
    var value: String
    var onClick: () -> Unit
}

interface SquareState : RState {
    var value: String
}

fun RBuilder.square(propValue: String, propOnClick: () -> Unit) = child(Square::class){
    attrs {
        value = propValue
        onClick = propOnClick
    }
}