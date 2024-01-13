package org.example

import org.junit.jupiter.api.Test

class InMemoryJanusgraphDatabaseTest {
    private val subject = InMemoryJanusgraphDatabase()

    @Test
    fun pairsMayBeAdded() {
        subject.addPair("a", "b")
    }
}