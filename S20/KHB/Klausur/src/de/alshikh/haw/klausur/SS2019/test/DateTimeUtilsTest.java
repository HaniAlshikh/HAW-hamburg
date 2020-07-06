package de.alshikh.haw.klausur.SS2019.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;

import de.alshikh.haw.klausur.SS2019.Classes.DateAndTimeUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DateTimeUtilsTest {
	private List<Long> testDataKilian = List.of(6L,7L,25L);
	private List<Long> testDataElias = List.of(1L,5L,15L);
	private List<Long> testDataQuentin = List.of(56L,3L,6L);//to be checked
	private List<Long> testDataHenryVIII = List.of(528L,0L,5L);//to be checked
	private List<Long> testDataAlbert = List.of(140L,7L,8L);//to be checked
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAge() {
		Map<TemporalUnit, Long> age01 = DateAndTimeUtils.getAge(LocalDate.of(2012, 11, 8));
		Map<TemporalUnit, Long> age02 = DateAndTimeUtils.getAge(LocalDate.of(2018, 1, 18));
		Map<TemporalUnit, Long> ageQuentin = DateAndTimeUtils.getAge(LocalDate.of(1963, 3, 27));
		Map<TemporalUnit, Long> ageHenryVIII = DateAndTimeUtils.getAge(LocalDate.of(1491, 6, 28));
		Map<TemporalUnit, Long> ageAlbert = DateAndTimeUtils.getAge(LocalDate.of(1878, 11, 25));

		assertEquals(testDataKilian.get(0), age01.get(ChronoUnit.YEARS));
		assertEquals(testDataKilian.get(1), age01.get(ChronoUnit.MONTHS));
		assertEquals(testDataKilian.get(2), age01.get(ChronoUnit.DAYS));
		assertEquals(testDataElias.get(0), age02.get(ChronoUnit.YEARS));
		assertEquals(testDataElias.get(1), age02.get(ChronoUnit.MONTHS));
		assertEquals(testDataElias.get(2), age02.get(ChronoUnit.DAYS));
		assertEquals(testDataQuentin.get(0), ageQuentin.get(ChronoUnit.YEARS));
		assertEquals(testDataQuentin.get(1), ageQuentin.get(ChronoUnit.MONTHS));
		assertEquals(testDataQuentin.get(2), ageQuentin.get(ChronoUnit.DAYS));
		assertEquals(testDataHenryVIII.get(0), ageHenryVIII.get(ChronoUnit.YEARS));
		assertEquals(testDataHenryVIII.get(1), ageHenryVIII.get(ChronoUnit.MONTHS));
		assertEquals(testDataHenryVIII.get(2), ageHenryVIII.get(ChronoUnit.DAYS));
		assertEquals(testDataAlbert.get(0), ageAlbert.get(ChronoUnit.YEARS));
		assertEquals(testDataAlbert.get(1), ageAlbert.get(ChronoUnit.MONTHS));
		assertEquals(testDataAlbert.get(2), ageAlbert.get(ChronoUnit.DAYS));
	}

	@Test
	public void testGetBinaryPalindromYears() {
		long yearCount = DateAndTimeUtils.getBinaryPalindromYears(
				LocalDate.of(1428,7,25),
				LocalDate.of(2427, 7, 25))
		        .count();
		assertEquals(3, yearCount);
	}

}
