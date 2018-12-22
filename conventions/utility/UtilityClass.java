
/*
*convention: we need to create a private constructor for utility 
*class in order to avoid the initialization of utility classes
*/
public class UtilityClass {
       // Suppress default constructor for noninstantiability
       private UtilityClass() {
           throw new AssertionError();
       }
       // ... (utility methods)
}