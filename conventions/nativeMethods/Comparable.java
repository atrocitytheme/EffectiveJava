package commonmethod;

import java.lang.reflect.Field;

/**
 * simple usage of Comparable is Arrays.sort(a)
 * principles:
   1. if the first object is less than the second, then the second must be greater than the first
        (similar to equals principle) and so is greater and equals
   2. if one object is greater than a second,
      and the second is greater than a third,
      then the first must be greater than the third

  Conclusion: reflexivity, symmetry, and transitivity

  3. If the argument is of the wrong type,
     the invocation won’t even compile.
     If the argument is null, the invocation should throw a NullPointerException
 */

public class Comparable {
    public static void main(String[] args){
        // e.g: implements java.util.Comparable ignored
        // use annoymous class instead and reflection to test the code, which is powerful
        // in javascript with call and bound statement, and in java itself, it's also useful
        // for framework development.
        CaseInsensitiveString s = new CaseInsensitiveString("asd") {
            public int compareTo(CaseInsensitiveString cis) throws IllegalAccessException {
                try {
                    Field data = cis.getClass().getDeclaredField("s");
                    data.setAccessible(true);
                    return String.CASE_INSENSITIVE_ORDER.compare("some string", (String) data.get(cis));
                } catch (NoSuchFieldException e) {
                    return 0;
                }
            }
        };
    }
}

/**
 * If a class has multiple significant fields, the order in which you compare them is critical
 * You must start with the most significant field and work your way down
   e.g:
         // Area codes are equal, compare prefixes
             if (prefix < pn.prefix)
             return -1;
             if (prefix > pn.prefix)
             return 1;
             // Area codes and prefixes are equal, compare line numbers
             if (lineNumber < pn.lineNumber)
             return -1;
             if (lineNumber > pn.lineNumber)
             return 1;
             return 0;  // All fields are equal


 While this method works, it can be improved.
 Recall that the contract for com- pareTo does not specify the magnitude of the return value,
 only the sign:

     public int compareTo(PhoneNumber pn) {
         // Compare area codes
         int areaCodeDiff = areaCode - pn.areaCode;
         if (areaCodeDiff != 0)
         return areaCodeDiff;
         // Area codes are equal, compare prefixes
         int prefixDiff = prefix - pn.prefix;
         if (prefixDiff != 0)
         return prefixDiff;
         // Area codes and prefixes are equal, compare line numbers
         return lineNumber - pn.lineNumber;
     }

     This trick works fine here but should be used with extreme caution, more generally, that the difference between the
     lowest and highest possible field values is less than or equal to Integer.MAX_VALUE (231-1).
 * */


