package Classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * For top-level (non-nested) classes and interfaces,
   there are only two possible access levels: package-private and public

 * A protected member is part of the class’s exported API
   and must be supported forever
 * Instance fields should never be public
   see in https://crunchify.com/java-tips-never-make-an-instance-fields-of-class-public/
 * classes with public mutable fields are not thread-safe

 * */
public class Minimizer {
    private int t;
    private class Tm {
        void call() {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {}
}

//e.g:
// public static final Thing[] VALUES =  { ... };
// the code above is a potential security hol
// solutions:

class example1 {
    private static final Thing[] PRIVATE_VALUES = {};

    public static final List<Thing> VALUES () {
        // questions solution in:
        // https://stackoverflow.com/questions/46579074/what-is-the-difference-between-list-of-and-arrays-aslist/46580435
        return Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
        // or use clone method
        //e.g:
        // return PRIVATE_VALUES.clone();
    }
}