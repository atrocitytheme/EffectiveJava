package reuse;
/*
* This program gets the right answer, but it is much slower than it should be,
* due to a one-character typographical error.
* The variable sum is declared as a Long instead of a long,
* which means that the program constructs
* about 231 unnecessary Long instances
* (roughly one for each time the long i is added to the Long sum)*/
public class AutoBoxing {
    public static void main(String[] args) {
        Long sum = 0L;
//        long sum = 0; //revise sum to long
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
