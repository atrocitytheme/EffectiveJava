package commonmethod;
/*
Four situations not to override equal:
* Each instance of the class is inherently unique
* You don’t care whether the class provides a “logical equality” test
* A superclass has already overridden equals, and the superclass behavior is appropriate for this class
* The class is private or package-private, and
  you are certain that its equals method will never be invoked
Principles:
* Reflexive: x == x
* Symmetric: x.equals(y) == y.equals(x)
* transitive: x.equals(y) == true && y.equals(z) == true => x.equals(z) == true
* Consistent: the boolean result of equals should remain in the whole lifecycle
* nullable: x.equals(null) == false
* */
public class Equal {
    public static void main(String[] args) {

    }
}

// e.g: do not write non-symmetric equals method

class CaseInsensitiveString {
    private final String s;
    public CaseInsensitiveString(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }
    // Broken - violates symmetry!
    @Override public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return s.equalsIgnoreCase(
                    ((CaseInsensitiveString) o).s);
        if (o instanceof String)  // One-way interoperability!
            return s.equalsIgnoreCase((String) o);
        return false;
    }
    // Remainder omitted
}
/*
 *As expected, cis.equals(s) returns true. The problem is that while the equals method
 in CaseInsensitiveString knows about ordinary strings, the equals method in String is oblivious to
 case-insensitive strings. Therefore s.equals(cis) returns false, a clear violation of symmetry.
 Suppose you put a case-insensitive string into a collection:
 ------------------------------------------------
   List<CaseInsensitiveString> list =
       new ArrayList<CaseInsensitiveString>();
   list.add(cis);
 ------------------------------------------------
  * What does list.contains(s) return at this point? Who knows? In Sun’s cur- rent implementation,
   it happens to return false, but that’s just an implementation artifact.
   In another implementation, it could just as easily return true or throw a runtime exception.
   Once you’ve violated the equals contract,
   you simply don’t know how other objects will behave when confronted with your object.

* */

//e.g:

class Color {}

class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object o) {
        if (!(o instanceof Point))
            return false;
        Point p = (Point) o;
        return p.x == x && p.y == y;
    }
}
/*
 Suppose you want to extend this class,
 adding the notion of color to a point:
*/

class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /*
    * The problem with this method is that you might get different
      results when comparing a point to a color point and vice versa.
      The former comparison ignores color,
      while the latter comparison always returns false
      because the type of the argument is incorrect.

    * It turns out that this is a fundamental problem of equivalence relations in object-oriented languages.
      There is no way to extend an instantiable class and add a value component while preserving
      the equals contract, unless you are willing to forgo the benefits of object-oriented abstraction.
    * */

    @Override public boolean equals(Object o) {
        if (!(o instanceof ColorPoint))
            return false;
        return super.equals(o) && ((ColorPoint) o).color == color; // extension: https://stackoverflow.com/questions/30604431/why-can-an-instance-of-a-class-access-private-fields-of-another-instance-of-its
    }

    /* !Important: You may hear it said that you can extend an instantiable class
        and add a value component while preserving the equals contract by using a getClass test in place of the
        instanceof test in the equals method:
   ----------------------------------------------------
        // Broken - violates Liskov substitution principle
       @Override public boolean equals(Object o) {
       if (o == null || o.getClass() != getClass())
           return false;
           Point p = (Point) o;
           return p.x == x && p.y == y;
        }
  --------------------------------------------------------
    While this may not seem so bad, the consequences are unacceptable.
  ---------------------------------------------------------

  // Initialize UnitCircle to contain all Points on the unit circle private static final Set<Point> unitCircle;

        static {
               unitCircle = new HashSet<Point>();
               unitCircle.add(new Point( 1,  0));
               unitCircle.add(new Point( 0,  1));
               unitCircle.add(new Point(-1,  0));
               unitCircle.add(new Point( 0, -1));
        }

        public static boolean onUnitCircle(Point p) {
           return unitCircle.contains(p);
        }
  ---------------------------------------------------------
    The Liskov substitution principle says that any important property of a type should also hold for its subtypes,
    so that any method written for the type should work equally well on its subtypes [Liskov87].
     But suppose we pass a Counter- Point instance to the onUnitCircle method.
     If the Point class uses a getClass- based equals method,
    the onUnitCircle method will return false regardless of the CounterPoint instance’s x and y values.
*/

}


/*
* exceptions: writing a disclaimer
  There are some classes in the Java platform libraries that do extend an instantiable class
  and add a value component.
  For example, java.sql.Timestamp extends java.util.Date and adds a nanoseconds field.
  The equals implementa- tion for Timestamp does violate symmetry and can cause erratic behavior
  if Timestamp and Date objects are
   used in the same collection or are otherwise inter- mixed. The Timestamp class has a disclaimer cautioning programmers
   against mixing dates and timestamps. While you won’t get into trouble as long as you keep them separate,
   there’s nothing to prevent you from mixing them, and the resulting errors can be hard to debug.
  This behavior of the Timestamp class was a mistake and should not be emulated.
* */

// Summary:

/*
* Putting it all together, here’s a recipe for a high-quality equals method:
1. Use the == operator to check if the argument is a reference to this object. If so, return true.
   This is just a performance optimization,
   but one that is worth doing if the comparison is potentially expensive.
2. Use the instanceof operator to check if the argument has the correct type.
If not, return false. Typically, the correct type is the class in which the method occurs. Occasionally,
it is some interface implemented by this class. Use an in- terface if the class implements an interface that
refines the equals contract to permit comparisons across classes that implement the interface.
Collection interfaces such as Set, List, Map, and Map.Entry have this property.

3. Cast the argument to the correct type. Because this cast was preceded by an instanceof test,
it is guaranteed to succeed.

4. For each “significant” field in the class,
check if that field of the argument
matches the corresponding field of this object.
5. When you are finished writing your equals method,
   sask yourself three questions:
    Is it symmetric?
    Is it transitive?
    Is it consistent?
6. Don’t substitute another type for Object in the equals declaration.
* */
