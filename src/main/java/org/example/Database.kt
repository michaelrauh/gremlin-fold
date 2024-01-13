package org.example

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

abstract class Database() {
    abstract fun initialize(): GraphTraversalSource
    abstract fun start_transaction()
    abstract fun commit_transaction()

    val g by lazy { initialize() }

    fun addPair(first: String, second: String) {
        start_transaction()
        val a: Vertex = g.addV("word").property("value", first).next()
        val b: Vertex = g.addV("word").property("value", second).next()

        g.addE("pair").from(a).to(b).next()
        commit_transaction()
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