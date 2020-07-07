package de.alshikh.haw.klausur.SS2018.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import de.alshikh.haw.klausur.SS2018.interfaces.IStack;
import de.alshikh.haw.klausur.SS2018.classes.Stack;
import static de.alshikh.haw.klausur.SS2018.interfaces.IStack.INITIAL_CAPACITY;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testcases for a class..
 * @author Bernd Kahlbrandt
 *
 */
class StackTest {
  private IStack<String> emptyStack;
  private IStack<String> defaultStack;
  private IStack<String> smallStringStack;
  private IStack<Integer> smallIntStack;
  private IStack<Integer> tenIntStack;
  /**
   * Build some stacks.
   * @throws Exception just in case anything went wrong
   */
  @BeforeEach
  void setUp() throws Exception {
    emptyStack = new Stack<>();
    defaultStack = new Stack<>();
    smallStringStack = new Stack<>(3);
    List.of("one", "two", "three").forEach(smallStringStack::push);
    smallIntStack = new Stack<>(3);
    List.of(1,2,3).forEach(smallIntStack::push);
    tenIntStack = new Stack<>();
    List.of(1,2,3,4,5,6,7,8,9,10).forEach(tenIntStack::push);
  }
/**
 * Tests some properties of the default constructor.
 */
  @Test
  void testStack() {
    assertNotNull(emptyStack);
    assertEquals(0, emptyStack.size());
    assertEquals(INITIAL_CAPACITY, emptyStack.capacity());
    Stream.iterate("a", s -> s + s)
          .limit(INITIAL_CAPACITY)
          .forEach(s -> defaultStack.push(s));
    assertEquals(INITIAL_CAPACITY, defaultStack.size());
    assertEquals(INITIAL_CAPACITY, defaultStack.capacity());
    defaultStack.push("Last");
    assertTrue(defaultStack.capacity()>INITIAL_CAPACITY);
    assertEquals("Last", defaultStack.pop());
  }
/**
 * Test the constructor with an integer parameter.
 */
  @Test
  void testStackInt() {
    assertEquals(3, smallStringStack.capacity());
    assertEquals(3, smallStringStack.size());
    assertThrows(NegativeArraySizeException.class, ()->new Stack<>(-1));
  }
/**
 * Very limited tests of some properties of {@link Stack#push}.
 */
  @Test
  void testPush() {
    List.of(1,2,3,4,5,6,7,8,9,10).forEach(smallIntStack::push);
    assertEquals(3, smallStringStack.size());
    assertEquals("three", smallStringStack.pop());
    assertEquals(13,smallIntStack.size());
    assertTrue(INITIAL_CAPACITY < smallIntStack.capacity());
  }
  /**
   * Very limited tests of some properties of {@link Stack#pop}.
   */

  @Test
  void testPop() {
    assertEquals("three", smallStringStack.pop());
    assertEquals(Integer.valueOf(3), smallIntStack.pop());
  }
  /**
   * Some limited tests of some properties of {@link Stack#push}
   * and {@link Stack#pop}.
   */
  @Test
  void testPopPush() {
    assertEquals(Integer.valueOf(10), tenIntStack.pop());
    assertEquals(Integer.valueOf(9), tenIntStack.pop());
    assertEquals("three", smallStringStack.pop());
    assertEquals(Integer.valueOf(3), smallIntStack.pop());
  }
  /**
   * Some limited tests of some properties of {@link Stack#peek}
   */

  @Test
  void testPeek() {
    assertEquals(Optional.empty(), emptyStack.peek());
    assertEquals(Optional.of(3), smallIntStack.peek());
    assertEquals(Optional.of(10), tenIntStack.peek());
    assertEquals(Optional.of(10), tenIntStack.peek());//make sure peek das not remove.

  }
  /**
   * Some very limited tests, to check the consistent implementation of
   * {@link Stack#hashCode()} and {@link Stack#equals(Object)}.
   */
  @Test
  void testEqualsHashConsistency() {
    IStack<Integer> tenIntStack2 = new Stack<>();
    List.of(1,2,3,4,5,6,7,8,9,10).forEach(tenIntStack2::push);
    assertEquals(tenIntStack, tenIntStack2);
    assertEquals(tenIntStack.hashCode(), tenIntStack2.hashCode());
  }
  /**
   * Make sure, that there are some required methods.
   */
  @Test
  void testSerialization00() {
    Class<?> [] interfaces = Stack.class.getInterfaces();
    assertTrue(Arrays.asList(interfaces).contains(Serializable.class));
    assertTrue(Arrays.asList(interfaces).contains(IStack.class));
    Method [] stackMethods = Stack.class.getDeclaredMethods();
    for(Method m : stackMethods) {
      if (m.getName().equals("writeObject")||m.getName().equals("readObject")) {
        if (m.getParameterTypes().length==1) {
          if(m.getModifiers() == Modifier.PRIVATE) {
            assertEquals(void.class, m.getReturnType());
          } else {
            assertTrue(false);
          }
        }else {
          assertTrue(false);
        }
      }
    }

    try {
      assertEquals("serialVersionUID", Stack.class.getDeclaredField("serialVersionUID").getName());
    } catch (NoSuchFieldException | SecurityException e) {
      fail("Haben Sie eventuell vergessen eine serialVersionUID zu definieren?");
    }
  }
  @Test
  void testSerialization01() {
    try(ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(ba)){
      oos.writeObject(tenIntStack);
      oos.writeObject(smallStringStack);
      ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(ba.toByteArray()));
      Object deser =  ois.readObject();
      if (deser instanceof IStack) {
        /**
         * The unchecked cast warning can not be prevented here, because the byte stream
         * may contain e.g. an IStack with another type parameter.
         */
      IStack<Integer> deserializedInt = (IStack<Integer>) deser;
      assertEquals(tenIntStack,deserializedInt);
      assertEquals(tenIntStack.peek().get(), deserializedInt.peek().get());
      /**
       * The unchecked cast warning can not be prevented here, because the byte stream
       * may contain e.g. an IStack with another type parameter.
       */
      IStack<String> deserializedString = (IStack<String>) ois.readObject();
      assertEquals(smallStringStack.peek().get(), deserializedString.peek().get());
      } else {
        throw new ClassNotFoundException();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  /**
   * Tests for some overwrites.
   */
  @Test
  public void testOverwrites() {
      try {
          emptyStack.getClass().getDeclaredMethod("toString",new Class<?> [0]);
          emptyStack.getClass().getDeclaredMethod("equals",Object.class);
          emptyStack.getClass().getDeclaredMethod("hashCode",new Class<?> [0]);
      } catch (NoSuchMethodException e) {
          fail("Könnte es sein, dass Sie vergessen haben, eine Methode wie "
              + "equals, hashCode oder toString zu überschreiben?");
      } catch (SecurityException e) {
          e.printStackTrace();
      }
  }

}
