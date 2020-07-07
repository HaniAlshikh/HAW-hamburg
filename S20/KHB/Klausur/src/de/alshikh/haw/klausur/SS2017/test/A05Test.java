//package de.alshikh.haw.klausur.SS2017.test;
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import de.hawh.kahlbrandt.ss2017bai2pm2exam.A05;
//
//
//public class A05Test {
//    private static Method [] methods;
//    private static List<Method> methodsToTest = new ArrayList<>();
//    private int [] empty = new int[0];
//    private int [] one = {1};
//    private int [] someInts = {42, 11, 8, 25};
//    private int [] someLargeInts = {Integer.MIN_VALUE, Integer.MAX_VALUE, 42};
//    private int [] minInts = {Integer.MIN_VALUE, -1};
//    private int [] maxInts = {Integer.MAX_VALUE, 1};
//    private int [] maxMinInts = {Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE};
//    @BeforeAll
//    public static void initTestBed(){
//        try {
//            methods = A05.class.getMethods();
//            Arrays.sort(methods, (a,b)-> a.toString().compareTo(b.toString()));
//            for(Method m : methods){
//                if(selectMethods(m)){
//                    methodsToTest.add(m);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @BeforeEach
//    public void setUp() throws Exception {
//    }
//    /**
//     * Tests
//     */
///**
// * Tests empty, one element and some with large values for a correct sum.
// */
//    @Test
//    public void testMethods() {
//        for(Method m: methodsToTest){
//        try {
//                assertEquals(m.getName(), 0, m.invoke(null,empty));
//                assertEquals(m.getName(), 1, m.invoke(null,one));
//                assertEquals(m.getName(), 86, m.invoke(null,someInts));
//                assertEquals(m.getName(), 41, m.invoke(null,someLargeInts));
//
//            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                System.out.println(e.getCause());
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testMethodsExceptions() {
//        for (Method m : methodsToTest) {
//            try {
//                assertThrows(java.lang.ArithmeticException.class, ()->m.invoke(null, minInts));
//                assertThrows(java.lang.ArithmeticException.class, ()->m.invoke(null, maxInts));
//                assertThrows(java.lang.ArithmeticException.class, ()->m.invoke(null, maxMinInts));
//
//            } catch (IllegalArgumentException e) {
//                if(e.getCause() instanceof ArithmeticException){
//                   throw (ArithmeticException)e.getCause();
//                }else{
//                e.printStackTrace();
//                }
//            }
//        }
//
//    }
//
//    @Test
//    public void testUtility(){
//        try {
//            Constructor<A05> constructor = A05.class.getDeclaredConstructor((Class<?>[])null);
//            assertTrue("Kein privater default constructor deklariert!",
//                    Modifier.isPrivate(constructor.getModifiers()));
//        } catch (NoSuchMethodException | SecurityException e) {
//                 e.printStackTrace();
//        }
//    }
//    /**
//     * Selects the static methods that return an int.
//     * @param m
//     * @return
//     */
//    private static boolean selectMethods(Method m) {
//        boolean selected = false;
//           if(m.getReturnType().equals(int.class)){
//                   if(Modifier.isStatic(m.getModifiers())){
//                       if(m.getParameterTypes().getClass().isArray()){
//                           selected = true;
//                           }
//                       }
//                   }
//
//        return selected;
//    }
//
//}
