```
public <T> T[] toArray(T[] a) {
       if (a.length < size)
return (T[]) Arrays.copyOf(elements, size, a.getClass()); System.arraycopy(elements, 0, a, 0, size);
if (a.length > size)
           a[size] = null;
       return a;
}
```

This code above causes uncheckd type warning, in order to 
use supresswarning within a smallest scope, we can store 
a middle varaible result: 

```
public <T> T[] toArray(T[] a) {
       if (a.length < size) {
// This cast is correct because the array we're creating 
// is of the same type as the one passed in, which is T[]. @SuppressWarnings("unchecked") T[] result =
               (T[]) Arrays.copyOf(elements, size, a.getClass());
           return result;
       }
       System.arraycopy(elements, 0, a, 0, size);
       if (a.length > size)
           a[size] = null;
       return a;
}
```