package org.example

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.janusgraph.core.JanusGraphFactory
import org.janusgraph.core.schema.SchemaAction
import org.janusgraph.graphdb.database.management.ManagementSystem


class InMemoryJanusgraphDatabase : Database() {
    override fun initialize(): GraphTraversalSource {
        val graph = JanusGraphFactory.open("janusgraph-local.conf")
        graph.tx().rollback() //Never create new indexes while a transaction is active
        val mgmt = graph.openManagement()
        mgmt.buildIndex("words", Vertex::class.java).addKey(mgmt.getOrCreatePropertyKey("word")).buildCompositeIndex()
        mgmt.buildIndex("values", Vertex::class.java).addKey(mgmt.getOrCreatePropertyKey("value")).buildCompositeIndex()
        mgmt.buildIndex("ortho", Vertex::class.java).addKey(mgmt.getOrCreatePropertyKey("ortho")).buildCompositeIndex()
        mgmt.commit()

        val g = graph.traversal()
        return g
    }

    override fun startTransaction() {
        g.tx().open()
    }

    override fun commitTransaction() {
        g.tx().commit()
    }

//        JanusGraphManagement mgmt = graph.openManagement();
//        mgmt.printSchema();
//        mgmt.commit();
//        graph.close()
}