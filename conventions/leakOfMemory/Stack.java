package LeakOfMemory;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
* There's a memory leak in this program:
 If a stack grows and then shrinks,
 the objects that were popped off the stack will not be garbage collected,
 even if the program using the stack has no more references to them.
 This is because the stack main- tains obsolete references to these objects.

Conslusion: Generally speaking,

* whenever a class manages its own memory,
  the programmer should be alert for memory leaks
* Another common source of memory leaks is caches
* A third common source of memory leaks is listeners and other callbacks

Weak Reference solution: 
e.g: WeakHashMap
* */
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        // To avoid leak:
        // Object result = elements[--size];
        // elements[size] = null; // Eliminate obsolete reference
        return elements[--size];  // any references outside of the “active portion”
                                 // of the element array are obsolete.
                                // The active portion consists of the elements whose index is less than size.
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}
