package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
* two types of stream: intermediate & terminal
* to make a functional interface compatible with lambda, add tag: @FunctionalInterface
  before declaring this interface

  https://stackoverflow.com/questions/22588518/lambda-expression-and-generic-method
* */
public class Concept {

    public static void main(String[] args) {
        List<Integer> c = new ArrayList<>(Arrays.asList(1, 2, 3));
        int sum = c.stream().filter((Integer w) -> {
            return w > 1;
        }).mapToInt(w -> w * 10).sum(); // filter and mapToInt are all intermediate manipulation, wrapping the steam to another
                                        // stream

        IntStream t = c.stream().filter((Integer w) -> {
            return w > 1;
        }).mapToInt(w -> w * 10);

        System.out.println(sum);
    }

}
