package de.alshikh.haw.generische.interfaces;

public interface Deque<E> {
    /**
     * Adds the element to the front of this Deque. Throws a {@link IllegalArgumentException} if this is not possible.
     * @param e
     */
    void addFirst(E e);
    /**
     * Adds the element to the end of this Deque. Throws a {@link IllegalArgumentException} if this is not possible.
     * @param e
     */
    void addLast(E e);
    /**
     * Adds the element to the top (end) of this Deque. (Stack terminology)
     * @param e The element to add.
     */
    void push(E e);
    /**
     * Adds the element to the front of this Deque.
     * @param e
     * @return true, if the element could be added, false otherwise.
     */
    boolean offerFirst(E e);
    /**
     * Adds the element to the end of this Deque.
     * @param e
     * @return true, if the element could be added, false otherwise.
     */
    boolean offerLast(E e);
    /**
     * Removes the first element from this Deque and returns it. Throws a {@link NoSuchElementException}
     * if this Deque is empty.
     * @return The first element
     */
    E removeFirst();
    /**
     * Removes the last element from this Deque and returns it. Throws a {@link NoSuchElementException}
     * if this Deque is empty.
     * @return The last element
     */
    E removeLast();
    /**
     * Removes the element from the top (end) of this Deque. (Stack terminology)
     */
    E pop();
    /**
     * Removes the first element from this Deque and returns it. Returns null
     * if this Deque is empty.
     * @return The last element if there is one, null otherwise
     */
    E pollFirst();
    /**
     * Removes the last element from this Deque and returns it. Returns null
     * if this Deque is empty.
     * @return The last element if there is any, null otherwise
     */
    E pollLast();
    /**
     * Returns the first element of this Deque. Throws a {@link NoSuchElementException}
     * if this Deque is empty.
     * @return The first element of this Deque or null if it is empty.
     */
    E getFirst();
    /**
     * Returns the last element of this Deque. Throws a {@link NoSuchElementException}
     * if this Deque is empty.
     * @return The last element of the Deque.
     */
    E getLast();
    /**
     * Returns the first element of this Deque. Returns null,
     * if this Deque is empty.
     * @return The first element of the Deque or null if this Deque is empty..
     */
    E peekFirst();
    /**
     * Returns the last element of this Deque. Return null,
     * if this Deque is empty.
     * @return The last element of the Deque or null if this Deque is empty..
     */
    E peekLast();
    /**
     * Returns the size of the Deque.
     * @return The size of the Deque.
     */
    int size();
}