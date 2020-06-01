package de.alshikh.haw.parallele_sequentielle_streams_IO.tests;

import de.alshikh.haw.parallele_sequentielle_streams_IO.classes.NumericalIntegration;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

/**********************************************************************
 *
 * basic tests for the NumericalIntegration class
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
class NumericalIntegrationTest {

    private Map<Function<Double,Double>, Double[]> testFx = Map.of(
            //   fx                   a  ,   b  ,   n   , estimation
            x -> x * x, new Double[]{10.0, 100.0, 1000.0, 332990.8580},
            Math::sin, new Double[]{0.0, Math.PI, 1000000.0, 2.0},
            x -> 1/x, new Double[]{50.0, 500.0, 10000.0, 2.302585}
    );

    @Test
    void integrateSequential() {
        testFx.forEach((fx, v) ->
            assertEquals(v[3], NumericalIntegration
                    .integrateSequential(v[0], v[1], v[2].intValue(), fx)
                    , 10e-3)
        );
    }

    @Test
    void integrateParallel() {
        testFx.forEach((fx, v) ->
                assertEquals(v[3], NumericalIntegration
                                .integrateParallel(v[0], v[1], v[2].intValue(), fx)
                        , 10e-3)
        );
    }
}