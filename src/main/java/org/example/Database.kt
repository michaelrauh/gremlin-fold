package org.example

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph
import org.janusgraph.core.JanusGraphFactory

class Database {
    fun foo(): Unit {
        val graph: Graph = TinkerGraph.open()
        val g = AnonymousTraversalSource.traversal().withEmbedded(graph)
        val marko = g.addV("person").property("name", "marko").property("age", 29).next()
        val lop = g.addV("software").property("name", "lop").property("lang", "java").next()
        g.addE("created").from(marko).to(lop).property("weight", 0.6).iterate()
        print(g.V().`in`())
        val v = JanusGraphFactory.open("conf/janusgraph-cql-es.properties")
        val foo = AnonymousTraversalSource.traversal().withEmbedded(v)
        val h = v.traversal()

        val mgmt = v.openManagement()
    }
}