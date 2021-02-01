package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Graph;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Graph04Benchmark {

    FordFulkerson fordFulkerson;
    EdmondsKarp edmondsKarp;

    @Setup(Level.Invocation)
    public void setUp() throws IOException {
        Graph graph = GraphParser
                .parseFile(Path.of("resource/graph04.gka"), "graph04");
        fordFulkerson = new FordFulkerson(graph, graph.getNode("q"), graph.getNode("s"));
        edmondsKarp = new EdmondsKarp(graph, graph.getNode("q"), graph.getNode("s"));
    }

    @TearDown
    public void finish() {
        System.out.println();
        System.out.println("-----------------------");
        System.out.println(fordFulkerson.maxFlow);
        System.out.println(edmondsKarp.maxFlow);
        System.out.println("-----------------------");
        System.out.println();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    public void graph04_Fulkerson() {
        fordFulkerson.solve();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    public void graph04_Karp() {
        edmondsKarp.solve();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Graph04Benchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
