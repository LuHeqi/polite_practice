package com.polite.java8nt.lambda;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.function.Consumer;


/**
 * @author polite
 * @date 2016-10-17 .
 */
public class Demo {
    public static void main(String[] args) {

        List<String> fixedLenStrings = Arrays.asList("d","a", "b", "c");
        fixedLenStrings.forEach(e -> System.out.println(e));

        fixedLenStrings.forEach(e -> {
            System.out.println("=======");
            System.out.println(e);
        });

        Consumer<String> c = e ->{
            System.out.println("===c0000000000000==");
        };
        Consumer<String > c1 = s -> {
            System.out.println("===c11111111111==");
            System.out.println(s);
        };

       fixedLenStrings.forEach(s -> {
           System.out.println("333333333333333");
           c1.accept(s);
       });
        fixedLenStrings.forEach(c.andThen(c1::accept));
        fixedLenStrings.forEach(c::accept);

        fixedLenStrings.sort((o1, o2) -> o1.compareTo(o2));
        fixedLenStrings.sort(String::compareTo);


        List<String> arrStrList = new ArrayList<>(fixedLenStrings);
        arrStrList.removeIf(s -> "a".equals(s));


        System.out.println(arrStrList);
    }
}
