package org.example

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

class Database() {
    private val graph: Graph = TinkerGraph.open()
    private val g = AnonymousTraversalSource.traversal().withEmbedded(graph)

    fun addPair(first: String, second: String) {
        val a: Vertex = g.addV("word").property("value", first).next()
        val b: Vertex = g.addV("word").property("value", second).next()

        g.addE("pair").from(a).to(b).next()
    }

    fun wordExists(word: String): Boolean {
        return g.V().hasLabel("word").has("value", word).hasNext()
    }

    fun pairExists(first: String, second: String): Boolean {
        return g.V()
            .hasLabel("word")
            .has("value", first)
            .out("pair")
            .has("value", second)
            .hasNext()
    }
}