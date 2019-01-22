package commonmethod;
/*
* principles:
1. Always override hashCode when overriding equals
2. the hashCode method must consistently return the same integer,
   provided no information used in equals
3. If two objects are equal according to the equals(Object) method,
   then calling the hashCode method on each of the two objects must
   produce the same integer result

4.It is not required that if two objects are unequal according to the equals(Object) method,
then calling the hashCode method on each of the two objects must produce distinct
integer results
* */


import java.util.Objects;

public class HashCode {
    public static void main(String[] args) {

        // https://stackoverflow.com/questions/14501233/unsigned-right-shift-operator-in-java
        System.out.println(1 >>> 1); // 0
        System.out.println(-1 >>> 1);
        System.out.println(3 >>> 1);
        long a = 1;
        long b = 2;
        System.out.println(a + b);
        System.out.println(Math.pow(2, 31));
        System.out.println(Integer.toBinaryString(-1 >>> 1));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println("1111111111111111111111111111111".length());

//        int c = 0L; // error: c should be int
//        long c = 0L;

        System.out.println(Float.floatToIntBits(0.15625f)); // 1042284544
        System.out.println(Integer.toBinaryString(1042284544));
        System.out.println("111110001000000000000000000000".length()); // ignore two zeros

        System.out.println(Double.doubleToLongBits(0.15626d)); // same above but in 64 bits format

        V1 v1 = new V1();
        V2 v2 = new V2();

        System.out.println(v1.hashCode());
        System.out.println(v2.hashCode());
    }
}

/*
* !Standard:

    1. find the data variable (field) in your class, e.g: x, y in Point class
       compute hashcode according to one of the field: e.g: x in Point class,
       Store some constant nonzero value, say, 17, in an int variable called result.

    2.
        a. follow the principles:

            i. If the field is a boolean, compute (f ? 1 : 0).

            ii. If the field is a byte, char, short, or int, compute (int) f.

            iii. If the field is a long, compute(int)(f^(f>>>32)). (see in HashCode above)

            iv. If the field is a float, compute Float.floatToIntBits(f). (see IEEE 754)

            v. If the field is a double, compute Double.doubleToLongBits(f),
            and then hash the resulting long as in step 2.a.iii.

            vi. If the field is an object reference and this class’s equals method compares the field by recursively invoking equals,
            recursively invoke hashCode on the field. If a more complex comparison is required, compute a “canonical representation”
            for this field and invoke hashCode on the canonical representation.
            If the value of the field is null, return 0 (or some other constant, but 0 is traditional).

            vii. If the field is an array,
            treat it as if each element were a separate field. That is,
            compute a hash code for each significant element by applying these rules recursively,
            and combine these values per step 2.b.
            If every element in an array field is significant, you can use one of the Arrays.

        b. Combine the hash code c computed in step 2.a into result as follows:
           result = 31 * result + c;

    3. Return result.

**/

/*
* e.g: @Override public int hashCode() {
           int result = 17;
           result = 31 * result + areaCode;
           result = 31 * result + prefix;
           result = 31 * result + lineNumber;
           return result;
}, where areaCode, prefix, lineNumber are all short
* */

/*
* notice:

 If a class is immutable and the cost of computing the hash code is significant,
 you might consider caching the hash code in the object rather than recalculating
 it each time it is requested

 e.g: in concurrent programming:


    private volatile int hashCode;  // cache the result of hashcode s.t we can
   @Override public int hashCode() {
       int result = hashCode;

       if (result == 0) { // if we haven't cached or do hashcode, we'll first implement this
           result = 17;
           result = 31 * result + areaCode;
           result = 31 * result + prefix;
           result = 31 * result + lineNumber;
           hashCode = result;
        }
       return result;
   }


* */

//simple version

class V1 {
    private String t = "1s";
    private long host = 10;
    private short host1 = 2;
    private float host2 = 1.2f;
    private double host3 = 1.3f;
    // naive way
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) (host ^ (host >>> 32));
        result = 31 * result + (int) host1;
        result = 31 * result + Float.floatToIntBits(host2);
        result = 31 * result + (int) Double.doubleToLongBits(host3);

        return result;
    }
}

class V2 {
    private String t = "1s";
    private long host = 10;
    private short host1 = 2;
    private float host2 = 1.2f;
    private double host3 = 1.3f;
    // simple way
    @Override
    public int hashCode() {
        return Objects.hash(host, host1, host2, host3);
    }
}
