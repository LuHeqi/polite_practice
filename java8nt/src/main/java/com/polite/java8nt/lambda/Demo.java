package com.polite.java8nt.lambda;

import com.polite.java8nt.model.Car;

import java.util.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * @author polite
 * @date 2016-10-17 .
 */
public class Demo {
    public static void main(String[] args) {

        // 2  函数组合与集合管道模式
        List<Car> cars = Arrays.asList(
                new Car("Jeep", "Wrangler", 2011),
                new Car("Jeep", "Comanche", 1990),
                new Car("Dodge", "Avenger", 2010),
                new Car("Buick", "Cascada", 2016),
                new Car("Ford", "Focus", 2012),
                new Car("Chevrolet", "Geo Metro", 1992)
        );

        List<String> models = cars.stream()
                .filter(c -> c.getYear() >= 2010)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel)
                .collect(Collectors.toList());
        models.forEach(m -> System.out.println(m));

        models.forEach(System.out::println);

        Map<String, Integer> pv = new HashMap<>();

        for (int i = 0; i < 4; i++) {
            pv.merge("a", 2, (oldValue, value) -> oldValue * value);
        }
        pv.put("b", 100);

        for (int i = 0; i < 4; i++) {
            pv.merge("b", 2, (oldValue, value) -> oldValue * value);
        }

        System.out.println(pv.get("a"));
        System.out.println(pv.get("b"));
        // ===================================== END ===============
        //3 传统 for 循环的函数式替代方案 BEGIN ===========
        /**
         * for 循环中定义的变量 i 是单个变量，它会在每次对循环执行迭代时发生改变。
         * range 示例中的变量 i 是拉姆达表达式的参数，所以它在每次迭代中都是一个全新的变量。
         * 这是一个细微区别，
         */
        System.out.print("Get set...");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "...");
        }
        System.out.print("Get set...");
        IntStream.range(1, 4)
                .forEach(i -> System.out.print(i + "..."));

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            int temp = i;

            executorService.submit(new Runnable() {
                public void run() {
                    //If uncommented the next line will result in an error
                    //System.out.println("Running task " + i);
                    //local variables referenced from an inner class must be final or effectively final
                    System.out.println("Running task " + temp);
                }
            });
        }

        /**
         * 与清单 3 中手动创建的 temp 非常相似，这个 i 参数在每次迭代中都表现为一个全新的变量。
         * 它是实际最终变量，因为我们不会在任何地方更改它的值。
         * 因此，我们可以直接在内部类的上下文中使用它 — 且不会有任何麻烦。
         */

        IntStream.range(0, 5)
                .forEach(i ->
                        executorService.submit(() -> System.out.println("IntStream Running task " + i)));
        //  显然，对于相对简单的迭代，使用 range 代替 for 具有一定优势，但 for 的特殊价值体现在于它能处理更复杂的迭代场景。

        /**
         * IntStream.iterate(1, e -> e + 3)
         *      .takeWhile(i -> i <= 100) //available in Java 9
         *      .sum()
         */
        //3 传统 for 循环的函数式替代方案 END ===========


        // 9 级联lambda 表达式 ：pivot -> candidate ->  candidate > pivot
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int total = totalSelectedValues(values, e -> e % 2 == 0);
        System.out.println(total);
        totalSelectedValues(values, Demo::isEven);
        int total2 = totalSelectedValues(values, createIsOdd());
        System.out.println(total2);

        List<Integer> odds = values.stream().filter(createIsOdd()).collect(Collectors.toList());
        odds.stream().reduce(0, Integer::sum);

        /**
         * 创建和重用 lambda 表达式:
         * https://www.ibm.com/developerworks/cn/java/j-java8idioms9/index.html
         *
         * 首先，函数接口 Function<T, U> 将一个 T 类型的输入转换为 U 类型的输出。例如，下面的示例将一个给定值转换为它的平方根：
         * Function<Integer, Double> sqrt = value -> Math.sqrt(value);
         * 在这里，返回类型 U 可以很简单，比如 Double、String 或 Person。或者它也可以更复杂，
         * 比如 Consumer 或 Predicate 等另一个函数接口。
         * 在本例中，我们希望一个 Function 创建一个 Predicate。所以代码如下：
         */
        Function<Integer, Predicate<Integer>> isGreatThan = (Integer pivot) -> {
            Predicate<Integer> isGreatThanPivot = (Integer candidate) -> {
                return candidate > pivot;
            };
            return isGreatThanPivot;
        };
        /**
         * 在本例中，外部 lambda 表达式接收 pivot 作为参数，内部 lambda 表达式接收 candidate 作为参数。
         * 内部 lambda 表达式的主体同时使用它收到的参数 (candidate) 和来自外部范围的参数。
         * 也就是说，内部 lambda 表达式的主体同时依靠它的参数和它的词法范围或定义范围。
         *
         * 简化后
         */

        Function<Integer, Predicate<Integer>> isGreatThan2
                = pivot -> candidate -> candidate > pivot;

        List<Integer> over5Valus = values
                .stream()
                .filter(isGreatThan2.apply(5))
                .collect(Collectors.toList());
        String joinStr = values
                .stream()
                .filter(isGreatThan2.apply(5))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        // ================ 9 级联lambda 表达式 end

        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        // Let's print all players before Java 8
        List<String> listOfAllPlayers = new ArrayList<>();

        for(List<String> team : playersInWorldCup2016){
            for(String name : team){
                listOfAllPlayers.add(name);
            }
        }

        System.out.println("Players playing in world cup 2016");
        System.out.println(listOfAllPlayers);


        // Now let's do this in Java 8 using FlatMap
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println("List of all Players using Java 8");
        System.out.println(flatMapList);


    }

    /**
     * 一个接收函数的函数
     * 在 Java™ 中，我们使用函数接口来引用 lambda 表达式和方法引用¾
     *
     * @param values
     * @param selector
     * @return
     */
    public static int totalSelectedValues(List<Integer> values, Predicate<Integer> selector) {
        return values
                .stream()
                .filter(selector)
             //   .reduce(0, (total, e) -> Integer.sum(total, e)) // 可以将这个 lambda 表达式替换为方法引用
                .reduce(0, Integer::sum)
                ;

    }

    /**
     * 一个返回函数的函数
     * 为了返回一个函数，我们必须提供一个函数接口作为返回类型。在本例中，我们的函数接口是 Predicate ×
     *
     * @return
     */
    public static Predicate<Integer> createIsOdd() {
       /*
        Predicate<Integer> check = (Integer number) -> number % 2 != 0;
        return check;
        */
        return number -> number % 2 != 0;
    }


    public static boolean isEven(Integer number) {
        return number % 2 == 0;
    }


}
