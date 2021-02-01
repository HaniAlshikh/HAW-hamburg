package de.hawh.hajs.gka03.util;

import de.hawh.hajs.gka03.util.algo.EdmondsKarpGKA;
import de.hawh.hajs.gka03.util.algo.FordFulkersonGKA;
import de.hawh.hajs.gka03.util.algo.error.FlowNotComputedError;
import de.hawh.hajs.gka03.util.generator.NetGenerator;
import org.graphstream.graph.Graph;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class FordVsEdmond {

    FordFulkersonGKA fordFulkerson;
    EdmondsKarpGKA edmondsKarp;
    Graph bigNet;

    // only generate once
    //@Setup
    //public void setGraph() throws IOException {
    //    bigNet = GraphParser.parseFile(Path.of("bigNet-saved.gka"), "bigNet");
    //}

    @Setup(Level.Invocation)
    public void setUp() throws IOException {
        // always regenerate
        Graph bigNet = NetGenerator.generate(50, 800,
                1.0, 100, "weight", "bigNet");
        fordFulkerson = new FordFulkersonGKA();
        fordFulkerson.init(bigNet, "q", "s");
        fordFulkerson.setCapacityAttribute("weight");
        edmondsKarp = new EdmondsKarpGKA();
        edmondsKarp.init(bigNet, "q", "s");
        edmondsKarp.setCapacityAttribute("weight");
    }

    @TearDown
    public void finish() {
        System.out.println();
        System.out.println("-----------------------");
        try {
            System.out.println(fordFulkerson.getMaximumFlow());
            System.out.println(fordFulkerson.getGraph());
        } catch (FlowNotComputedError e) {}
        try {
            System.out.println(edmondsKarp.getMaximumFlow());
            System.out.println(edmondsKarp.getGraph());
        } catch (FlowNotComputedError e) {}
        System.out.println("-----------------------");
        System.out.println();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    public void fulkerson() {
        fordFulkerson.compute();
        //System.out.println(fordFulkerson.getMaximumFlow());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    public void karp() {
        edmondsKarp.compute();
        //System.out.println(edmondsKarp.getMaximumFlow());
    }

    public static void main(String[] args) throws RunnerException {
        // only generate once
        //Graph bigNet = NetGenerator.generate(50, 800, 1.0,
        //        100.0, "weight", "bigNet");
        //Graph bigNet = NetGenerator.generate(800, 300_000,
        //        1.0, 100, "weight", "bigNet");
        //Graph bigNet = NetGenerator.generate(2500, 2_000_000, 1.0, 10.0, "weight", "bigNet");

        //GraphParser.saveToFile(bigNet);

        Options opt = new OptionsBuilder()
                .include(FordVsEdmond.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
