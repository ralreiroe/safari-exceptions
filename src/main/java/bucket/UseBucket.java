package bucket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UseBucket {
  public static void main(String[] args) {
    Bucket<String> bs = new Bucket<>(
        Arrays.asList("Fred", "Jim", "Sheila")
    );

    bs.forEach(s -> System.out.println("> " + s));

    bs
        .filter(s -> s.length() > 3)
        .map(s -> s.toUpperCase()) // Map operation -> "functor"
        .forEach(s -> System.out.println(">>> " + s));
    System.out.println("---------------------------");
    new Bucket<String>(Arrays.asList())
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println(s));

    System.out.println("---------------------------");
    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");
    String firstName = "Fred";

    String lastName = names.get(firstName);
    if (lastName != null) {
      String message = "Dear " + lastName.toUpperCase();
      System.out.println(message);
    }

    System.out.println("Bucket: ---------------------");
    new Bucket<>(Arrays.asList(names)) // Java 8 Optional
        .map(m -> m.get(firstName))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .forEach(s -> System.out.println(s));

    System.out.println("Optional: ---------------------");
    Optional.of(names) // Java 8 Optional
        .map(m -> m.get(firstName))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .ifPresent(s -> System.out.println(s));
  }
}
