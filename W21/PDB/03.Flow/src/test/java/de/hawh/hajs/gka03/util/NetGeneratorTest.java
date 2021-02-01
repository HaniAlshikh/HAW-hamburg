package de.hawh.hajs.gka03.util;

import de.hawh.hajs.gka03.util.generator.NetGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NetGeneratorTest {

    @Test
    void testMaximumEdges() {
        for (int i = 0; i < 100; i++) {

            assertDoesNotThrow(() -> {
                NetGenerator.generate(1, 0, 0.0, 100.0, "weight");
            });

            assertDoesNotThrow(() -> {
                NetGenerator.generate(2, 1, 0.0, 100.0, "weight");
            });

            assertDoesNotThrow(() -> {
                NetGenerator.generate(3, 3, 0.0, 100.0, "weight");
            });

            assertThrows(IllegalArgumentException.class, () -> {
                NetGenerator.generate(1, 1, 0.0, 100.0, "weight");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                NetGenerator.generate(2, 2, 0.0, 100.0, "weight");
            });
            assertThrows(IllegalArgumentException.class, () -> {
                NetGenerator.generate(2, 4, 0.0, 100.0, "weight");
            });

        }
    }
}
