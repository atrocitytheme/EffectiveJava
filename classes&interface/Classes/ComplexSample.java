import java.math.BigInteger;

/**
* The alternative to making an immutable class final is to make
* all of its constructors private or package-private,
* and to add public static factories in place of the public constructors (Item 1).
* */

public final class ComplexSample {
    private final double re;
    private final double im;

    private ComplexSample(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }
    /**
     * suppose that you want to provide a means of creating a com- plex
     * number based on its polar coordinates.
     * This would be very messy using con- structors
     * With static factories it’s easy. Just add a second static factory with
     * a name that clearly identifies its function
     * */
    public static Complex valueOfPolar(double r, double theta) {
        return new Complex(r * Math.cos(theta),
                r * Math.sin(theta));
    }

    /**
     * If you write a class whose security depends on the immutability of a BigInteger or BigDecimal
     * argument from an untrusted client, you must check to see that the argument is a “real” BigInteger or BigDecimal,
     * rather than an instance of an untrusted subclass.
     * If it is the latter, you must defen- sively copy it under the assumption that it might be mutable
     * */

    public static BigInteger safeInstance(BigInteger val) {
        if (val.getClass() != BigInteger.class)
            return new BigInteger(val.toByteArray());
        return val;
    }

}

/**
 * While this approach is not commonly used, it is often the best alternative.
 * It is the most flexible because it allows the use of multiple package-private
 * implemen- tation classes. To its clients that reside outside its package,
 * the immutable class is effectively final
 * because it is impossible to extend a class that comes from another package
 * and that lacks a public or protected constructor.
 * Besides allowing the flexibility of multiple implementation classes,
 * this approach makes it possible to tune the performance of the class in subsequent
 * releases by improving the object- caching
 * capabilities of the static factories.
 * */