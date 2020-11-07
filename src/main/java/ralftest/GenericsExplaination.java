package ralftest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

//declaring stuff - all generic
interface Collector<T, A, R> {
    Supplier<A> supplier();

    BiConsumer<A, T> accumulator();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

    Set<java.util.stream.Collector.Characteristics> characteristics();
}

//more declaring stuff - all generic
class CollectorImpl<T, A, R> implements java.util.stream.Collector<T, A, R> {
    private final Supplier<A> supplier;
    private final BiConsumer<A, T> accumulator;
    private final BinaryOperator<A> combiner;
    private final Function<A, R> finisher;
    private final Set<Characteristics> characteristics;

    CollectorImpl(Supplier<A> supplier,
                  BiConsumer<A, T> accumulator,
                  BinaryOperator<A> combiner,
                  Function<A, R> finisher,
                  Set<Characteristics> characteristics) {
        this.supplier = supplier;
        this.accumulator = accumulator;
        this.combiner = combiner;
        this.finisher = finisher;
        this.characteristics = characteristics;
    }

    @Override
    public BiConsumer<A, T> accumulator() {
        return accumulator;
    }

    @Override
    public Supplier<A> supplier() {
        return supplier;
    }

    @Override
    public BinaryOperator<A> combiner() {
        return combiner;
    }

    @Override
    public Function<A, R> finisher() {
        return finisher;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }

};


public class GenericsExplaination {

    public static void main(String[] args) {

        //now we are creating objects
        //the compiler needs to  generic types  replaced with real types
        final List<String> oldStaffDoesNotHaveConsistentTokens = List.of("reserve-adjustment.metrics", "Other");
        final List<String> activeFlagsCanBeMissing = List.of("active");
        Stream<List<String>> str = Stream.of(oldStaffDoesNotHaveConsistentTokens, activeFlagsCanBeMissing);
        Stream<String> stringStream = str.flatMap(List::stream);

        List<String> collect = stringStream.collect(
                new CollectorImpl<String, List<String>, List<String>>(
                        () -> new ArrayList(),  //binds Supplier<A> to Supplier<ArrayList<String>>
                        (a, s) -> a.add(s),
                        (a1, a2) -> {
                            a1.addAll(a2);
                            return a1;
                        },
                        (a) -> a,
                        Set.of(java.util.stream.Collector.Characteristics.IDENTITY_FINISH)
                )
        );

        System.out.println("collect = " + collect);


    }


}
