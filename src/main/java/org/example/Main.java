package org.example;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;
import org.janusgraph.core.schema.JanusGraphManagement;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

public class Main {
    public static void main(String[] args) {
        JanusGraph graph = JanusGraphFactory.open("janusgraph-local.conf");
        GraphTraversalSource g = graph.traversal();
        g.addV("here").property("foo", "bar").next();
        System.out.println(g.V().toList());
//        JanusGraphManagement mgmt = graph.openManagement();
//        mgmt.printSchema();
//        mgmt.commit();
        graph.close();


    }
}