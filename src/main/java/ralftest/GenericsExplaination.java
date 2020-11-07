package ralftest;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GenericsExplaination {

    public static void main(String[] args) {


        //collection of lambdas.
        //these are not used until this is passed to a eg. Stream<String>
        //    <R, A> R collect(Collector<? super T, A, R> collector);
        Collector<String, ArrayList<String>, List<String>> mycollector = new Collector<String, ArrayList<String>, List<String>>() {

            @Override
            public Supplier<ArrayList<String>> supplier() {
                return () -> new ArrayList<>();
            }

            @Override
            public BiConsumer<ArrayList<String>, String> accumulator() {
                return (l, e) -> l.add(e);
            }

            @Override
            public BinaryOperator<ArrayList<String>> combiner() {
                return (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                };
            }

            @Override
            public Function<ArrayList<String>, List<String>> finisher() {
                return al -> (List<String>) al;
            }

            @Override
            public Set<Characteristics> characteristics() {
                return EnumSet.of(Collector.Characteristics.IDENTITY_FINISH);
            }
        };
    }





}
