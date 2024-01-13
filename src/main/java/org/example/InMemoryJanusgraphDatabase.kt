package org.example

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.janusgraph.core.JanusGraphFactory


class InMemoryJanusgraphDatabase: Database() {
    override fun initialize(): GraphTraversalSource {
        val graph = JanusGraphFactory.open("janusgraph-local.conf")
        val g = graph.traversal()
        return g
    }

    override fun start_transaction() {
        g.tx().open()
    }

    override fun commit_transaction() {
        g.tx().commit()
    }

//        JanusGraphManagement mgmt = graph.openManagement();
//        mgmt.printSchema();
//        mgmt.commit();
//        graph.close()
}