package org.example

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DatabaseTest {
    private val subject: Database = TameDatabase()

    @Test
    fun databaseCreatesPairsAndEdgesWhenBothWordsAreNew() {
        subject.addPair("a", "b")

        assertTrue(subject.wordExists("a"))
        assertTrue(subject.wordExists("b"))
        assertTrue(subject.pairExists("a", "b"))
    }
}

class TameDatabase : Database() {
    override fun initialize(): GraphTraversalSource {
        val graph: Graph = TinkerGraph.open()
        val g = AnonymousTraversalSource.traversal().withEmbedded(graph)
        return g
    }

    override fun start_transaction() {}

    override fun commit_transaction() {}
}

