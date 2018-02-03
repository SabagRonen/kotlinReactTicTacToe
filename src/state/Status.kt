package state

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div

class Status : RComponent<StatusProps, RState>() {
    override fun RBuilder.render() {
        div { + props.title }
    }
}

interface StatusProps : RProps {
    var title: String
}

fun RBuilder.status(title: String) = child(Status::class){
    attrs.title = title
}