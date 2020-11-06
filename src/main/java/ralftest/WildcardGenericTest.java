package ralftest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WildcardGenericTest {

    //can only deal with List<Object> as input
    public static void printList(List<Object> list) {
        for (Object elem : list)
            System.out.println(elem + " ");
        System.out.println();
    }

    //can take List<String> ,List<Integer> etc.
    // we could do this but there is no need for T!!
    public static <T> void printListT(List<T> list) {
        for (T elem: list)
            System.out.print(elem + " ");
        System.out.println();
    }

    //why use ? instead of T? because the code does not depend on T
    //When the code is using methods in the generic class that don't depend on the type parameter.
    // For example, List.size or List.clear. In fact, Class<?> is so often used because most of the methods in Class<T> do not depend on T.
    //https://docs.oracle.com/javase/tutorial/java/generics/unboundedWildcards.html
    //
    public static void printListW(List<?> list) {
        for (Object elem: list)
            System.out.print(elem + " ");
        System.out.println();
    }

    //example from rt.jar:
//    public static <T>
//    Collector<T, ?, List<T>> toList() {
//        return new Collectors.CollectorImpl<>((Supplier<List<T>>) ArrayList::new, List::add,
//                (left, right) -> { left.addAll(right); return left; },
//                CH_ID);
//    }

    public static void main(String[] args) {

        //we have to pass a list of objects
        List<Object> li = Arrays.asList(1, 2, 3);
        List<Object>  ls = Arrays.asList("one", "two", "three");
        printList(li);
        printList(ls);

        //we can pass a List of specific types
        List<Integer> lis = Arrays.asList(1, 2, 3);
        List<String>  lss = Arrays.asList("one", "two", "three");
        printListW(lis);
        printListW(lss);

    }
}
