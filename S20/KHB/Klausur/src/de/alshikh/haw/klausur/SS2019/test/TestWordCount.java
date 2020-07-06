package de.alshikh.haw.klausur.SS2019.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;

import de.alshikh.haw.klausur.SS2019.Classes.A01;
import org.junit.jupiter.api.Test;

class TestWordCount {

/**
 * Tests some values (highest, non existence) in the returned map.
 */
	@Test
	void test() {
		Map<String, Long> frequencies = A01.wordFrequency("./src/de/alshikh/haw/klausur/SS2019/Data/picasso.txt");
		assertEquals(1, frequencies.get("juan"));
		assertEquals(7, frequencies.get("der"));
		assertEquals(3, frequencies.get("picasso"));
		assertNull(frequencies.get("Picasso"));
		assertNull(frequencies.get("Gernhardt"));
	}

}
