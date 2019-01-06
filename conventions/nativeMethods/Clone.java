package commonmethod;
/**
*  if you override the clone method in a nonfinal class, you should return an object obtained by invoking super.clone
*  clone is broken in java, the clone method isn't declared in Clonable interface, Cloneable interface is used
*  merely to avoid CloneNotSupportedException when involving super.clone() on overriding clone method for an object
* */

import java.util.Arrays;
import java.util.EmptyStackException;

public class Clone implements Cloneable {
    @Override
    public Clone clone() {
        Clone d = null;
        try {
             d = (Clone) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("asd");
        } finally {
            System.out.println("finally");
        }

        return d;
    }

    public static void main(String[] args) {
        Clone c = new Clone();
        Clone m = c.clone();
        System.out.println(m);
        Stack stack = new Stack();
        Stack s = stack.clone();
        System.out.println(s);
    }
}

// e.g 2:
class Stack implements Cloneable{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    // Ensure space for at least one more element.
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    @Override public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

// e.g deepcopy:
/**
 * // Iteratively copy the linked list headed by this Entry
 *    Entry deepCopy() {
 *       Entry result = new Entry(key, value, next);
 * for (Entry p = result; p.next != null; p = p.next)
 * p.next = new Entry(p.next.key, p.next.value, p.next.next);
 *       return result;
 *    }
 *
 */