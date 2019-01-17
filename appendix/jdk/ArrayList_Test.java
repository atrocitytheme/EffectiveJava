package jdk;

import java.util.Arrays;

/**
 * Test the source code implementation of the jdk ArrayList
 * currently tested:
 * toArray() may not return Object[]
 * why use transient keyword for elementData
 * */
public class ArrayList_Test {
    public static void main(String[] args) {
        testArrayReturn();
        testObjectPassing(new TestObj()); // false, false
        testObjectPassing(new Object()); // true, false
        SystemArrayCopyTest();
    }

    /**
     * Test whether the array will return result other than
     * Object[]
     * */
    private static void testArrayReturn() {
        System.out.println("test Array Return: ...");
        Object[] obj = new Object[5];
        obj[0] = new Object();
        System.out.println(obj.getClass() == Object[].class); // true
        Object[] tb = new TestObj[5];
//        tb[0] = new Object(); // throw exception since only TestObj can be stored (we initialize it with sub class)
        tb[1] = new TestObj();
        System.out.println(tb.getClass() == Object[].class); // false
    }

    /**
     * check the object parameter, whether in runtime, it's determined by the real object
     * */

    private static void testObjectPassing(Object tt) {
        System.out.println("test the Object whether is determined by the argument");
        System.out.println(tt.getClass().equals(Object.class));
        TestObj c = new TestObj();
        System.out.println(c.getClass().equals(Object.class));
    }
    /**
     * check the remove procedure of arraylist
     * */
    private static void SystemArrayCopyTest() {
        System.out.println("test system.arrayCopy method");
        Integer[] c = new Integer[] {1,2,3,4,5,6,7,8};
        int index = 3;
        int numMove = c.length - 1 - index;
        System.arraycopy(c, index + 1, c, index, numMove);
        c[c.length - 1] = null;
        System.out.println(Arrays.toString(c));
    }

    private static class TestObj {}
}