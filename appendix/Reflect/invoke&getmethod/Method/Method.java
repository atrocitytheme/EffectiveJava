package Method;

import java.util.LinkedList;
import java.util.List;
import java.lang.*;

public class Method <T extends Function>{
    // we should extends Function, otherwise, duck typing is not
    // supported in java
    // if we don't need <T extends> we can do it in this way:
    // f.getClass().getMethod(...).invoke(f)

    public List<T> funcs = new LinkedList<>();

    public void push(T m) {
        funcs.add(m);
    }

    public void callAll(){
        for (T f : funcs) {
            f.call();

            try {
                f.getClass().getMethod("call").invoke(f, new Object[0]);
            } catch (Exception e) {
                System.out.println("method failed!");
                System.out.println(e);
                // fail here not because the public method, because the anonymous classes
                // are not public, so their instance
                // if we insist on calling this "call" method try setAccessible(true) below
            }


            // example successful code
            try {
                java.lang.reflect.Method m = f.getClass().getMethod("call");
                m.setAccessible(true);
                m.invoke(f);
                System.out.println("succeed!");

            } catch (Exception e) {
                 System.out.println("no!!!");
            }

        }
    }

}
