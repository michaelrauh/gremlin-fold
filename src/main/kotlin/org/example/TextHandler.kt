package org.example

import java.nio.file.Path
import kotlin.io.path.readText

class TextHandler(filename: Path) {
    private val fileContents: String = filename.readText()

    fun getPairs(): List<Pair<String, String>> {
        return fileContents.split(".", "!", "?", ";").filter { it.length > 1 }
            .map { it.lowercase().replace(Regex("[^A-Za-z0-9 ]"), "").trim() }
            .flatMap { sentence -> sentence.split(" ").windowed(2).map { Pair(it[0], it[1]) } }.distinct()
    }
}
