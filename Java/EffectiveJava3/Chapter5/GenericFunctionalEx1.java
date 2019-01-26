package effectivejava.chapter5.item30;

import java.util.List;
import java.util.function.BiFunction;

public class GenericFunctionalEx1 {
    private <T> void merge(T t1, T t2, BiFunction<T, T, Boolean> biFunction){
        if(biFunction.apply(t1, t2)) {
            doSomething();
        } else {
            nothing();
        }
    }

    private void nothing() {
        System.out.println("Nothing");
    }

    private void doSomething() {
        System.out.println("Do Something");
    }


    private void integersMerge() {
        BiFunction<Integer, Integer, Boolean> IntegerBiFunction = (Integer a, Integer b) -> {
            return a > b;
        };

        merge(2, 5, IntegerBiFunction);
    }

    private void booleanMerge() {
        BiFunction<Boolean, Boolean, Boolean> booleanBiFunction = (Boolean a, Boolean b) -> {
            return a.equals(b);
        };

        merge(true, true, booleanBiFunction);
    }
}
