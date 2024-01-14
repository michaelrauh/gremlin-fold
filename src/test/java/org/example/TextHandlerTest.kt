package org.example

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.io.path.writeText

class TextHandlerTest {
    @Test
    fun getsPairsFromAFile() {
        val file = kotlin.io.path.createTempFile("test.txt")
        file.writeText("This iS text? it has words has words")

        val subject = TextHandler(file)
        val pairs = subject.getPairs()

        assertEquals(
            listOf(
                Pair("this", "is"),
                Pair("is", "text"),
                Pair("it", "has"),
                Pair("has", "words"),
                Pair("words", "has")
            ), pairs
        )
    }
}

