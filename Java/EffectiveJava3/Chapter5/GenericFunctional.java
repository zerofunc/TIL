package effectivejava.chapter5.item30;

import java.util.List;
import java.util.function.BiFunction;

public class GenericFunctional {
    private <T> void merge(T t1, T t2, BiFunction<T, T> biFunction){
        if(t1)
    }

    private void nothing() {
        System.out.println("Nothing");
    }

    private void doSomething() {
        System.out.println("Do Something");
    }


    private void integersMerge() {
        (Integer a, Integer b) -> {
            System.out.println("a :" + a);
            System.out.println("b :" + b);
        };

        merge(2, 5, integerIntegerObjectBiFunction);
    }

    private void booleanMerge() {
        BiFunction<Boolean, Boolean, Boolean> booleanBiFunction = (Boolean a, Boolean b) -> {
            return a.equals(b);
        };

        merge(true, true, booleanBiFunction);
    }
}
