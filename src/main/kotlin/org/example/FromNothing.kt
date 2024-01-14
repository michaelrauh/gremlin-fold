package org.example

class FromNothing(private val db: Database) {
    fun go() {
        db.fromNothing()
        db.countSquares()
    }
}
