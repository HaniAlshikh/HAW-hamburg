package de.hawhamburg.hamann.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * An Implementation of a list as required by the HAWList interface.
 *
 * @author Hanik Alshikh
 * @author Johan Kemper
 * @author Jannik Stuckstätte
 *
 * @param <E> The type of all elements inside this HAWListImpl.
 */
public class HAWListImpl<E> implements HAWList<E> {

    private Object[] elements;
    private int size;
    private static final int CAPACITY_EXTENSION = 20;

    public HAWListImpl() {
        elements = new Object[CAPACITY_EXTENSION];
        this.size = 0;
    }

    /**
     * Returns the number of elements inside this HAWListImpl.
     *
     * @return Number of elements inside this HAWListImpl.
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Adds an element to the end of this HAWListImpl.
     *
     * @param item The item to be added to this HAWListImpl.
     */
    @Override
    public void add(E item) {
        this.ensureFreeCapacity();
        this.elements[size] = item;
        this.size++;
    }

    /**
     * Returns this HAWListImpl as a string in the form of: '[Element1, Element2, ...]'.
     *
     * @return The string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E element : this) {
            sb.append(element.toString());
            sb.append(", ");
        }
        // Remove trailing comma and whitespace
        if (size > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Checks if the given Object o is equal to this Object.
     *
     * @param o The object to be compared to this.
     * @return Whether this object is equal to o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HAWList)) {
            return false;
        }
        HAWList<?> hawList = (HAWList<?>) o;
        return isHawListEqual(hawList);
    }

    /**
     * Calculates an hashcode for this HAWListImpl.
     *
     * @return The hashcode calculated.
     */
    @Override
    public int hashCode() {
        int result = 1;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        return result;
    }

    /**
     * Sorts this list ascending by using the given comparator. This implementation uses an
     * iterative approach instead of using recursion.
     *
     * @param o Der <code>Comperator</code> zum Vergleich bei der Sortierung.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort(Comparator<E> o) {
        // Initializing the stack to iterate over the boundaries
        Stack<Entry<Integer, Integer>> stack = new Stack<>();
        // Boundaries for the whole list
        stack.push(Map.entry(0, this.size - 1));

        // Iterating until there are no more boundaries in stack to sort
        while (!stack.empty()) {
            // Removing and getting the next range to sort
            Entry<Integer, Integer> currentStackValues = stack.pop();

            // Retrieving left and right boundary from Entry for better readability
            Integer left = currentStackValues.getKey();
            Integer right = currentStackValues.getValue();

            // Skipping this range if it contains less than two elements.
            if (right <= left) {
                continue;
            }

            // Initializing moving indices
            int l = right;
            int r = left;

            // Choosing last element in range as pivot element
            E pivotElement = (E) elements[right];

            // Swapping elements until moving indices crossed
            while (r < l) {
                // Incrementing moving index "r" until an element is found that is bigger or equal
                // than pivot element or end of boundary is reached
                while (o.compare((E) elements[r], pivotElement) < 0 && r < right) {
                    r++;
                }

                // Decrementing moving index "l" until an element is found that is smaller
                // than pivot element or start of boundary is reached
                while (o.compare((E) elements[l], pivotElement) >= 0 && l > left) {
                    l--;
                }

                // Checking if moving indices didnt cross already and swap elements if not
                if (r < l) {
                    swapElements(l, r);
                }
            }
            // Swapping pivot element in between the two partitions
            swapElements(r, right);
            // Adding partition containing all smaller elements to the stack
            stack.push(Map.entry(left, l));
            // Adding partition containing all bigger or equal elements to the stack
            // excluding pivot element
            stack.push(Map.entry(r + 1, right));
        }
    }

    /**
     * Override as required from Iterable interface.
     *
     * @return An Iterator for this HAWListImpl.
     */
    @Override
    public Iterator<E> iterator() {
        return new HAWListIterator();
    }

    /**
     * Inner Iterator class for usage in iterator()
     */
    private class HAWListIterator implements Iterator<E> {

        private int currentIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements. (In other words, returns {@code
         * true} if {@link #next} would return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (hasNext()) {
                return (E) elements[currentIndex++];
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Swaps the element at indexOne with the element at indexTwo of this HAWListImpl.
     *
     * @param indexOne The index of the first element to swap.
     * @param indexTwo The index of the other element to swap the first with.
     */
    @SuppressWarnings("unchecked")
    private void swapElements(int indexOne, int indexTwo) {
        E tempElement = (E) elements[indexOne];
        elements[indexOne] = elements[indexTwo];
        elements[indexTwo] = tempElement;
    }

    /**
     * This method checks if there is still free capacity inside this HAWListImpl. If there isn't it
     * extends the capacity by CAPACITY_EXTENSION.
     */
    private void ensureFreeCapacity() {
        if (this.size < this.elements.length) {
            return;
        }
        this.extendCapacity();
    }

    /**
     * Extends the capacity of this HAWListImpl by CAPACITY_EXTENSION.
     */
    private void extendCapacity() {
        this.elements = Arrays.copyOf(this.elements, elements.length + CAPACITY_EXTENSION);
    }

    /**
     * Checks for every element in listToCheck if it is equal to the element of this HAWListImpl at
     * the same index.
     *
     * @param listToCheck The list to compare to this HAWListImpl.
     * @return Whether every element of this HAWListImpl and listToCheck at the same index are
     * equal.
     */
    private boolean isHawListEqual(HAWList<?> listToCheck) {
        Iterator<?> iteratorOfThis = this.iterator();
        Iterator<?> iteratorOfOther = listToCheck.iterator();

        while (iteratorOfThis.hasNext() && iteratorOfOther.hasNext()) {
            if (!iteratorOfThis.next().equals(iteratorOfOther.next())) {
                return false;
            }
        }

        return !iteratorOfThis.hasNext() && !iteratorOfOther.hasNext();
    }
}
