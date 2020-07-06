package de.alshikh.haw.klausur.SS2019.Interfaces;

import de.alshikh.haw.klausur.SS2019.Exceptions.EmptyQueueException;

/**
 * An Interface to be used for a Queue in a lab task.
 * @author Bernd Kahlbrandt
 *
 */
public interface IQueue<E> {
	/**
	 * Inserts an element into the Queue and returns a 
	 * reference to the Queue, so that enqueues can be chained.
	 * @param element The element to be inserted
	 * @return This Queue
	 */
	IQueue<E> enqueue(E element);
	/**
	 * Removes the first (oldest) element in the Queue and returns a 
	 * reference to the Queue, so that dequeues can be chained.
	 * @return The first element.
	 */
	IQueue<E> dequeue() throws EmptyQueueException;
	/**
	 * Returns the first element of the Queue without removing it.
	 * @return This Queue
	 * @throws EmptyQueueException if the Queue is empty;
	 */
	E peek() throws EmptyQueueException;	
	/**
	 * Returns the size of the queue.
	 * @return
	 */
	int size();
	/**
	 * Checks if the Queue is empty.
	 * @return <b>true</b> if the Queue is empty, <b>false</b> otherwise.
	 */
	boolean isEmpty();
}
