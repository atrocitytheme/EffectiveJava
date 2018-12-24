package LeakOfMemory;

import java.util.Map;
import java.util.WeakHashMap;
/*
weak memory for cache to avoid memory leak process

for query of weak/strong reference, check 
https://javarevisited.blogspot.com/2014/03/difference-between-weakreference-vs-softreference-phantom-strong-reference-java.html

for more information on garbage collection, see:

https://www.cnblogs.com/sunniest/p/4575144.html

for types of leak, see: 
https://www.jianshu.com/p/54b5da7c6816
*/
public class WeakMap {
    public static void main(String[] args) {
        Map<String, String> test = new WeakHashMap<>();
        String str3 = "test";
        test.put("str1", "a"); //
        test.put("str2", "b"); // won't be removed since it has reference in primitive arrea
        test.put(str3, "c");

        System.out.println(test.containsKey("str1")); // true
        System.out.println(test.get("str1")); // a
        System.out.println(test.get(str3)); // c
        System.out.println(test.containsKey(str3)); // true

        System.gc();

        System.out.println(test.get(str3)); // c
        System.out.println(test.containsKey(str3)); // true
        System.out.println(test.get("str2")); // b
        System.out.println(test.containsKey("str2")); // true
        System.out.println(test.containsKey("str1")); // false
        str3 = null;
        System.gc();
        System.out.println(test.get(str3)); // null
        System.out.println(test.containsKey(str3));// false
        System.out.println(test.get("str2")); // b
        System.out.println(test.containsKey("str2")); // true
        System.out.println(test.containsKey("str1")); // false
    }
}
