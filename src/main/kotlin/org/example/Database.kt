package org.example

import org.apache.tinkerpop.gremlin.groovy.jsr223.dsl.credential.`__`.select
import org.apache.tinkerpop.gremlin.groovy.jsr223.dsl.credential.`__`.unfold
import org.apache.tinkerpop.gremlin.process.traversal.P.eq
import org.apache.tinkerpop.gremlin.process.traversal.P.neq
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal.Symbols.values
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Column
import org.apache.tinkerpop.gremlin.structure.Vertex

abstract class Database() {
    abstract fun initialize(): GraphTraversalSource
    abstract fun startTransaction()
    abstract fun commitTransaction()

    val g by lazy { initialize() }

    fun wordExists(word: String): Boolean {
        startTransaction()
        val hasNext = g.V().hasLabel("word").has("value", word).hasNext()
        commitTransaction()
        return hasNext
    }

    fun pairExists(first: String, second: String): Boolean {
        startTransaction()
        val hasNext = g.V().hasLabel("word").has("value", first).out("pair").has("value", second).hasNext()
        commitTransaction()
        return hasNext
    }

    fun addPairs(allPairs: List<Pair<String, String>>) {
        startTransaction()
        (allPairs.map { it.first } + allPairs.map { it.second }).distinct().forEach {
            g.addV("word").property("value", it).next()
        }
        allPairs.forEach { pair ->
            val a = g.V().has("value", pair.first).next()
            val b = g.V().has("value", pair.second).next()
            g.addE("pair").from(a).to(b).next()
        }
        commitTransaction()
    }

    fun fromNothing() {
        startTransaction()
        g.V().hasLabel("word").`as`("a").out("pair").`as`("b").out("pair").`as`("d").`in`("pair").`as`("c").`in`("pair")
            .`as`("a_prime").where("a", eq("a_prime")).where("b", neq("c")).select<Vertex>("a", "b", "c", "d")
            .by("value").dedup()
            .by(select<String, String>("b", "c").select<String>(Column.values).local(unfold<Iterable<Any>>().order().fold()))
            .addV("ortho").`as`("o").addE("hasWord").from("o").to("a").addE("hasWord").from("o").to("b").addE("hasWord")
            .from("o").to("c").addE("hasWord").from("o").to("d").iterate()
        commitTransaction()
    }

    fun countSquares(): Long {
        startTransaction()
        val count = g.V().hasLabel("ortho").count().next()
        commitTransaction()
        return count
    }
}