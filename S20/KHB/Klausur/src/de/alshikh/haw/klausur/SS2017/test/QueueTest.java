package de.alshikh.haw.klausur.SS2017.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.alshikh.haw.klausur.SS2017.Interfaces.IQueue;
import de.alshikh.haw.klausur.SS2017.exception.QueueEmptyException;
import de.alshikh.haw.klausur.SS2017.exception.QueueFullException;
import de.alshikh.haw.klausur.SS2017.classes.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testfälle für die Klasse Queue in der Klausur B-AI2 PM2, SS 2017.
 * @author Bernd Kahlbrandt
 *
 */
public class QueueTest {
    private IQueue<Integer> intQueueEmpty;
    private List<String> stringInput = new ArrayList<>();
    private IQueue<String> stringQueue;
    private IQueue<String> stringQueueFull;
    private IQueue<Integer> intQueue;

    @BeforeEach
    public void setUp() throws Exception {
        intQueueEmpty = new Queue<>();
        intQueue = new Queue<>(1);
        stringInput = Stream.generate(new Supplier<String>() {
            String start = "";

            @Override
            public String get() {
                return start += "Abc";
            }
        }).limit(10).collect(Collectors.toList());
        stringQueueFull = new Queue<>(10);
        stringQueue = new Queue<>(11);
        for (String s : stringInput) {
            stringQueueFull.enqueue(s);
            stringQueue.enqueue(s);
        }
    }
    /**
     * Tests the default constructor.
     * It checks if the Queue has the specified default capacity.
     * Exception handling kept to a minimum.
     */
    @Test
    public void testQueue(){
        try {
            Field cap = IQueue.class.getField("DEFAULT_CAPACITY");
            cap.setAccessible(true);
            int entriesLength = getEntries(intQueueEmpty);
            assertEquals(cap.get(null), entriesLength);
            assertEquals(42, IQueue.DEFAULT_CAPACITY);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private static int getEntries(IQueue<?> queue) throws IllegalAccessException {
        int entriesLength = -1;
        for(Field f : Queue.class.getDeclaredFields()){
            f.setAccessible(true);
            if(f.getType().isArray()){
               entriesLength = Array.getLength(f.get(queue));
           }
        }
        return entriesLength;
    }
    /**
     * Checks if the capacity is the one passed to the constructor.
     */
    @Test
    public void testQueueInt(){
        IQueue<String> stringTest = new Queue<>(11);
        try {
            assertEquals(11,getEntries(stringTest));
            assertEquals(1,getEntries(new Queue<String>(1)));
            assertEquals(Short.MAX_VALUE,getEntries(new Queue<String>(Short.MAX_VALUE)));

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        IQueue<String> localStringQueue = new Queue<>(10);
        for(String s : stringInput){
            localStringQueue.enqueue(s);
        }
        assertTrue(localStringQueue.isFull());
    }
    /**
     * Checks for detection of negative capacity {@link Queue}.
     */
    @Test
    public void testQueueIntException(){
      assertThrows(NegativeArraySizeException.class, () -> new Queue<>(-42));
    }
    /**
     * Checks for detection of negative capacity {@link Queue}.
     */
    @Test
    public void testQueueIntExceptionMax(){
      assertThrows(NegativeArraySizeException.class, () -> new Queue<>(Integer.MIN_VALUE));
    }
    /**
     * Checks for detection of non positive capacity {@link Queue}.
     */
    @Test
    public void testQueueCapacityZero(){
        assertThrows(NegativeArraySizeException.class, () -> new Queue<>(0));
    }
    /**
     * Checks correct enqueues using peek.
     */
    @Test
    public void testEnqueueWithPeek() {
        for(String s:stringInput){
            assertEquals(s,stringQueue.peek().get());
            stringQueue.dequeue();
        }
    }
    /**
     * Takes a full queue and enqueues one more element. Excepts a {@link QueueFullException}.
     */
    @Test
    public void testEnqueueFullException01() {
      assertThrows(QueueFullException.class, () -> stringQueueFull.enqueue("Overflow"));
    }
    /**
     * Takes a queue of capacity 2 and enqueues one more element. Excepts a {@link QueueFullException}.
     */
    @Test
    public void testEnqueueFullException02() {
        intQueue.enqueue(42);
        assertThrows(QueueFullException.class, () -> intQueue.enqueue(47));
    }

    /**
     * Takes a queue of capacity 1 and enqueues one more element. Excepts a {@link QueueFullException}.
     */
    @Test
    public void testEnqueueFullException03() {
      intQueue.enqueue(47);
      assertThrows(QueueFullException.class,()->intQueue.enqueue(42));

    }

/**
 * Dequeues an element from a preconstructed Queue and checks, if the following peek gets the
 * correct element.  Checks if peek delivers an empty {@link Optional}, if the {@link Queue}
 * is empty.
 */
    @Test
    public void testDequeueWithPeek() {
        stringQueue.dequeue();
        assertEquals(stringQueue.peek().get(),"AbcAbc");
        assertFalse(intQueueEmpty.peek().orElse(-1).equals(0));
//        assertThat(intQueueEmpty.peek().orElse(-1),equalTo(-1));
    }
/**
 * Checks if {@link Queue#dequeue()} on an empty {@link Queue}throws an {@link QueueEmptyException}.
 */
    @Test
    public void testDequeueException() {
        assertThrows(QueueEmptyException.class, () -> intQueueEmpty.dequeue());
    }
 /**
  * Checks results of {@link Queue#peek} on an empty as well as non empty {@link Queue}.
  */
    @Test
    public void testPeek() {
        assertFalse(intQueueEmpty.peek().isPresent(), "Optional ungültig?");
        assertTrue(stringQueue.peek().isPresent(), "Invalid Optional");
        assertEquals("Abc", stringQueue.peek().get());
    }
    /**
     * Tests the result of {@link Queue#isEmpty()} in a few cases.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(intQueueEmpty.isEmpty());
        assertFalse(stringQueue.isEmpty());
    }
    /**
     * Tests the result of {@link Queue#isFull()} in a few cases.
     */
   @Test
    public void testIsFull() {
        assertEquals(false, intQueueEmpty.isFull());
        assertEquals(false, intQueue.isFull());
        assertEquals(true, stringQueueFull.isFull());

    }
   /**
    * Tests the result of {@link Queue#isFull()} in a few cases, where a {@link QueueFullException}
    * geworfen werden sollte.
    */
   @Test
    public void testIsFullExeption() {
        stringQueue.enqueue("Hugo");
        assertFalse(intQueueEmpty.isFull());
        assertThrows(QueueFullException.class,()->stringQueue.enqueue("Victor"));

    }
    @Test
    public void testInheritedMethods() {
        try {
            intQueueEmpty.getClass().getDeclaredMethod("toString",new Class<?> [0]);
        } catch (NoSuchMethodException e) {
            fail("Könnte es sein, dass Sie vergessen haben, eine Methode zu überschreiben?");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tests the consistency of {@link Queue#equals(Object)} and {@link Queue#hashCode()},
     * but only in a rudimentary way.
     */
    @Test
    public void testEqualsHashCode(){
        IQueue<Integer> newQueue = new Queue<>();
        int hashBefore = newQueue.hashCode();
        newQueue.enqueue(1);
        int hashAfter = newQueue.hashCode();
        intQueueEmpty.enqueue(1);
        if(newQueue.equals(intQueueEmpty)){
            assertEquals(newQueue.hashCode(),intQueueEmpty.hashCode());
        }
        //assertEquals(hashBefore, hashAfter); can't be the equal
    }

}
