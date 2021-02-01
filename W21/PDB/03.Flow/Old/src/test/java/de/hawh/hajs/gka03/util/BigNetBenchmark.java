package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Graph;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class BigNetBenchmark {

    FordFulkerson fordFulkerson;
    EdmondsKarp edmondsKarp;
    Graph bigNet = GraphGenerator.generate("BigNet", 50, 800,
            1, 20,
            GraphGenerator.GraphType.DIRECTED);

    @Setup(Level.Invocation)
    public void setUp() throws IOException {
        fordFulkerson = new FordFulkerson(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
        edmondsKarp = new EdmondsKarp(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
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
    public void Fulkerson() {
        fordFulkerson.solve();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    public void Karp() {
        edmondsKarp.solve();
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BigNetBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
