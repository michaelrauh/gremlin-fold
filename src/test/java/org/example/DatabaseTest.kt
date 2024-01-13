package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DatabaseTest {
    private val subject = Database()

    @Test
    fun databaseCreatesPairsAndEdgesWhenBothWordsAreNew() {
        subject.addPair("a", "b")

        assertTrue(subject.wordExists("a"))
        assertTrue(subject.wordExists("b"))
        assertTrue(subject.pairExists("a", "b"))
    }
}

