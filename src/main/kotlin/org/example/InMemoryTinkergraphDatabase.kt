package org.example

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph

class InMemoryTinkergraphDatabase : Database() {
    override fun initialize(): GraphTraversalSource {
        val graph: Graph = TinkerGraph.open()
        val g = AnonymousTraversalSource.traversal().withEmbedded(graph)
        return g
    }

    override fun startTransaction() {}

    override fun commitTransaction() {}
}
