package streams;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ErrorHandling {
  private static Pattern WORD = Pattern.compile("\\W+");

  public static Optional<Stream<String>> getLines(String filename) {
    try {
      return Optional.of(Files.lines(Paths.get(filename)));
    } catch (Throwable t) {
      // Either would permit me to express what went wrong
      return Optional.empty();
    }
  }

  //  public static Stream<String> getLines(String filename) {
//    try {
//      return Files.lines(Paths.get(filename));
//    } catch (Throwable t) {
//      throw new RuntimeException(t);
//    }
//  }
  public static void main(String[] args) {
//    Stream.of("hello this is a sentence.", "this too is many words")
//        .flatMap(s -> WORD.splitAsStream(s))  // flatMap makes this "Monad"
//        .forEach(s -> System.out.println(s));

    Stream.of("a.txt", "b.txt", "c.txt")
//        .flatMap(n -> getLines(n)) // this one for the exception throwing version
        .map(s -> getLines(s))
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
