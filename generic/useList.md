 First, arrays are covariant. This scary-sounding word means simply that if Sub is a subtype of Super, then the array type Sub[] is a subtype of Super[]. Generics, by contrast, are invariant: for any two distinct types Type1 and Type2, List<Type1> is neither a subtype nor a supertype of List<Type2> 

## This will cause difference in problem casting, one is in 
the compile phase, good!(List), while the other one is at runtime, which is not good (array)


e.g: 

```
 // Fails at runtime!
Object[] objectArray = new Long[1];
objectArray[0] = "I don't fit in"; // Throws ArrayStoreException
```

```
// Won't compile!
List<Object> ol = new ArrayList<Long>(); // Incompatible types ol.add("I don't fit in");
```