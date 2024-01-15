package org.example

import java.io.File

class LocalFolder() {
    fun fold() {
        val textHandler = TextHandler(File("input.txt").toPath())
        val db = InMemoryJanusgraphDatabase()

        Pairs(textHandler, db).go()
        FromNothing(db).go()
        Up(textHandler, db).go()
        Over(textHandler, db).go()
    }
}
