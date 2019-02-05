while 
```
// Use of raw type for unknown element type - don't do this!
   static int numElementsInCommon(Set s1, Set s2) {
       int result = 0;
       for (Object o1 : s1)
           if (s2.contains(o1))
               result++;
       return result;
}

```
is not safe, since during the procedure, someone can still do operations like 
```
s1.add("string1")
```
at Runtime, which may cause later problems. However, when we switch to the wildcard convention:
```
// Unbounded wildcard type - typesafe and flexible
static int numElementsInCommon(Set<?> s1, Set<?> s2) {
       int result = 0;
       for (Object o1 : s1)
           if (s2.contains(o1))
               result++;
       return result;
   }
```
According to [stackoverflow](https://stackoverflow.com/questions/14242174/difference-between-an-unbound-wildcard-and-a-raw-type), we know that wildcard prevents us from doing dangerous operations like `s1.add("string1") // won't compile`.