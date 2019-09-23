package streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<E, F> {
  F apply(E e) throws Throwable; // generics can't handle runtime type...
}

public class ErrorWrapping {
  private static Pattern WORD = Pattern.compile("\\W+");

  public static <E, F> Function<E, Optional<F>> wrap(ExFunction<E, F> op) {
    return e -> {
      try {
        // return Either.success(op.apply(e));
        return Optional.of(op.apply(e));
      } catch (Throwable t) {
        // return Either.failure(t);
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "c.txt")
        .map(wrap(n -> Files.lines(Paths.get(n))))
        .peek(o -> {
          if (!o.isPresent()) { // Java 9 add isEmpty()...
            System.out.println("Problem opening file..");
          }
        })
        .filter(o -> o.isPresent())
        .flatMap(o -> o.get())
        .forEach(s -> System.out.println(s));
  }
}
