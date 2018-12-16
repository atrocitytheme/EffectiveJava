package builder;
/* this is the  JavaBeans pattern or handling too many parameters of
    a constructor
    But this pattern has temporary field problem and JavaBean may be
    in an inconsistent state partway through its construction

    The class does not have the option of enforcing consistency merely
    by checking the validity of the con- structor parameters.

    1. Attempting to use an object when it’s in an inconsistent state (created with no parameter/property ) may cause
    failures that are far removed from the code containing the bug,
    hence difficult to debug

    2. Make a class with no invariant, everything is mutable

    appendix:
    https://stackoverflow.com/questions/22472018/why-java-builder-pattern-over-java-bean-pattern
    https://nicogiangregorio.wordpress.com/2012/12/28/javabeans-an-anti-pattern-or-not/

*/
public class NewNutritionFacts {
    private int servingSize = -1; // Required; no default value
    private int servings = -1;
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NewNutritionFacts() { }
    // Setters
    public void setServingSize(int val) {servingSize = val;}
    public void setServings(int val) {servings = val;}
    public void setCalories(int val) {calories = val;}
    public void setFat(int val) {fat = val;}
    public void setCarbohydrate(int val) {carbohydrate = val;}
    public void setSodium(int val) {sodium = val;}
    public static void main(String[] args) {

        // the way to initialize this instance
        NewNutritionFacts cocaCola = new NewNutritionFacts();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);
    }
}
