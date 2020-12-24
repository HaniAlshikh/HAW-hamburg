package de.hawhamburg.hamann.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

class HAWListImplTest {

    private static final String[] NAMES = new String[]{"Bob", "Cyd", "Ada", "Ina", "Ivy", "Fed", "Dan", "Cyd", "Dee", "Cas"};
    private static final int[] SINGLE_NUMBER = new int[]{1};
    private static final int[] TWO_NUMBERS_UNSORTED = new int[]{2, 1};
    private static final int[] TWO_NUMBERS_SORTED = new int[]{1, 2};
    private static final int[] NUMBERS_UNSORTED = new int[]{1, 5, 3, 2, 3, 2, 2, 5, 4, 9, 20, 1};
    private static final int[] NUMBERS_SORTED = new int[]{1, 2, 3, 4, 7, 8, 9, 9, 20, 10};
    private static final int[] NUMBERS_SORTED_UNIQUE = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int[] NUMBERS_REPEATED = new int[]{1, 1, 1, 1, 1, 1, 1};
    private static final int[] NUMBERS_SORTED_MULTIPLE = new int[]{1, 1, 2, 2, 3, 3};


    // LIST IMPLEMENTATION TESTS

    @org.junit.jupiter.api.Test
    void add() {
        HAWList<String> list = new HAWListImpl<>();
        assertEquals(0, list.getSize());

        list.add("h");
        assertEquals(1, list.getSize());

        list.add("i");
        assertEquals(2, list.getSize());

        list = createStringList(NAMES);
        assertEquals(NAMES.length, list.getSize());
    }


    @org.junit.jupiter.api.Test
    void iterator() {
        HAWList<String> list = createStringList(NAMES);
        int count = 0;

        System.out.println(list);
        for(String s : list) {
            count++;
        }

        assertEquals(list.getSize(), count);
    }

    // QUICK SORT TESTS

    @org.junit.jupiter.api.Test
    void sortNames() {
        HAWList<String> list = createStringList(NAMES);
        assertCorrectSorting(list, String::compareTo);
    }

    @org.junit.jupiter.api.Test
    void sortSingleNumber() {
        HAWList<Integer> list = createIntegerList(SINGLE_NUMBER);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortTwoNumbersUnsorted() {
        HAWList<Integer> list = createIntegerList(TWO_NUMBERS_UNSORTED);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortTwoNumbersSorted() {
        HAWList<Integer> list = createIntegerList(TWO_NUMBERS_SORTED);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortNumbersUnsorted() {
        HAWList<Integer> list = createIntegerList(NUMBERS_UNSORTED);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortNumbersSorted() {
        HAWList<Integer> list = createIntegerList(NUMBERS_SORTED);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortNumbersSortedUnique() {
        HAWList<Integer> list = createIntegerList(NUMBERS_SORTED_UNIQUE);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }


    @org.junit.jupiter.api.Test
    void sortNumbersRepeated() {
        HAWList<Integer> list = createIntegerList(NUMBERS_REPEATED);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortNumbersSymmetric() {
        HAWList<Integer> list = createIntegerList(NUMBERS_SORTED_MULTIPLE);
        assertCorrectSorting(list, Comparator.naturalOrder());
    }

    @org.junit.jupiter.api.Test
    void sortEmptyList() {
        HAWList<Integer> list = new HAWListImpl<>();
        assertDoesNotThrow(() -> list.sort(Comparator.naturalOrder()));
    }




    private HAWList<String> createStringList(String[] elementsToAdd) {
        HAWList<String> list = new HAWListImpl<>();

        for (String element : elementsToAdd) {
            list.add(element);
        }

        return list;
    }

    private HAWList<Integer> createIntegerList(int[] elementsToAdd) {
        HAWList<Integer> list = new HAWListImpl<>();

        for (Integer element : elementsToAdd) {
            list.add(element);
        }

        return list;
    }

    private <E> void assertCorrectSorting(HAWList<E> list, Comparator<E> comparator) {
        list.sort(comparator);

        E previousElement = null;

        for (E currentElement : list) {
            if (previousElement != null) {
                assertTrue(comparator.compare(previousElement, currentElement) <= 0);
            }
            previousElement = currentElement;
        }
    }
}