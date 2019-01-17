/**
 To make a class immutable, follow these five rules
 * Don’t provide any methods that modify the object’s state
 * Ensure that the class can’t be extended
 * Make all fields final
 * Make all fields private
 * Ensure exclusive access to any mutable components.
 * Immutable objects are inherently thread-safe; they require no synchroni- zation.
 * */

public class Mutability {

}

// e.g:
final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex c) {

        return new Complex(re + c.re, im + c.im);
    }

    public Complex subtract(Complex c) {
        return new Complex(re - c.re, im - c.im);
    }
    public Complex multiply(Complex c) {
        return new Complex(re * c.re - im * c.im,
                re * c.im + im * c.re);
    }

    public Complex divide(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re * c.im) / tmp);
    }

    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Complex))
            return false;
        Complex c = (Complex) o;

        return Double.compare(re, c.re) == 0 &&
        Double.compare(im, c.im) == 0;
    }

    private int hashDouble(double val) {

        long longBits = Double.doubleToLongBits(val);

        return (int) (longBits ^ (longBits >>> 32));
    }

    @Override public int hashCode() {
        int result = 17 + hashDouble(re);
        result = 31 * result + hashDouble(im);
        return result;
    }

    @Override public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}

// Immutable classes should take advantage of this by encouraging clients to reuse existing instances wherever
//possible. One easy way to do this is to provide public static final constants for fre- quently used values.

//e.g:  public static final Complex ZERO = new Complex(0, 0);
//   public static final Complex ONE  = new Complex(1, 0);
//   public static final Complex I    = new Complex(0, 1);

//. Internally, the immuta- ble class can be arbitrarily clever. For example,
// BigInteger has a package-private mutable “companion class” that
// it uses to speed up multistep operations such as modular exponentiation.

/*
*  Recall that to guarantee immutability, a class must not permit itself to be subclassed.
*  Typically this is done by making the class final,
 *  but there is another, more flexible way to do it. The alternative to making an immutable class final
 *  is to make all of its constructors private or package-private,
 *  and to add public static factories in place of the public constructors (Item 1).
 *  for more https://stackoverflow.com/questions/36285031/inheritance-and-static-factory-methods
* */

// e.g:

class Com {
    private final double re;
    private final double im;

    private Com(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Com valueOf(double re, double im) {
        return new Com(re, im);
    }
}

//*Classes should be immutable unless there’s a very good reason to make them mutable.*