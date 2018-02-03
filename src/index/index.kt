package index

import game.game
import kotlinext.js.requireAll
import react.dom.*
import kotlin.browser.document

fun main(args: Array<String>) {
    requireAll(kotlinext.js.require.context("src", true, js("/\\.css$/")))

    render(document.getElementById("root")) {
        game()
    }
}