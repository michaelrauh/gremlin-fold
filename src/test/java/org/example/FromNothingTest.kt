package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FromNothingTest {
    @Test
    fun generatesSquaresFromPairs() {
        val db = InMemoryTinkergraphDatabase()
        db.addPairs(listOf(Pair("a", "b"), Pair("c", "d"), Pair("a", "c"), Pair("b", "d")))
        val subject = FromNothing(db)

        subject.go()

        assertEquals(1, db.countSquares())
    }
}