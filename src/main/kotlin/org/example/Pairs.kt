package org.example

class Pairs(private val textHandler: TextHandler, private val db: Database) {
    fun go() {
        db.addPairs(textHandler.getPairs())
    }
}
