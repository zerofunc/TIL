package effectivejava.chapter5.item30;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class GenericFunctionalEx2 {
    private <T> void something(List<T> list, Consumer<T> consumer){
        System.out.println("list Size : " + list.size());
        System.out.println("Start ");
        for (T t : list) {
            consumer.accept(t);
        }
    }

    private void integersSomthing() {
        Consumer<Integer> integerConsumer = (Integer i) -> {
            System.out.println("i :" + i);
            if(i > 3) {
                System.out.println("Bigger than 3");
            } else {
                System.out.println("3 or less");
            }
        };

        something(Arrays.asList(1,2,3,4,5), integerConsumer);
    }

    private void booleanSomething() {
        Consumer<Boolean> booleanConsumer = (Boolean b) -> {
            System.out.println("b : " + b);
        };

        something(Arrays.asList(true,true,false), booleanConsumer);
    }
}
