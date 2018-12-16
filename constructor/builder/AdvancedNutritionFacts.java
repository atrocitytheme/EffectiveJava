package builder;
/*
* Builder pattern combines the safety of the telescoping and
* readability of javabeans
*
* appendix:
* The Builder pattern does have disadvantages of its own.
* In order to create an object, you must first create its builder.
* While the cost of creating the builder is unlikely to be noticeable in practice,
* it could be a problem in some performance- critical situations.
* Also, the Builder pattern is more verbose than the telescoping constructor pattern,
* so it should be used only if there are enough parameters
* */

// Builder Pattern
public class AdvancedNutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // required parameters
        private final int servingSize;
        private final int servings;
        // optional parameters
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize, int servings) {
            // only two invariants need to be placed in this builder
            this.servingSize = servingSize;
            this.servings    = servings;
        }


        public Builder calories(int val)
            { calories = val;
            return this; }

        public Builder fat(int val)
        { fat = val;           return this; }

        public Builder carbohydrate(int val)
        { carbohydrate = val;  return this; }

        public Builder sodium(int val)
        { sodium = val;        return this; }

        public AdvancedNutritionFacts build() {
            return new AdvancedNutritionFacts(this);
        }
    }

    private AdvancedNutritionFacts(Builder builder) {
        servingSize  = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static void main() {
        // the usage of builder pattern
        AdvancedNutritionFacts cocaCola = new Builder(240, 8).
                                               calories(100).
                                               sodium(35).
                                                carbohydrate(27).
                                                build();
        // now the code is easy to read and the property in nutritution instances cannot be modified
    }
}
