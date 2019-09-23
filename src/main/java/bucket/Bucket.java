package bucket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Bucket<E> implements Iterable<E> {
  private Iterable<E> self;
  public Bucket(Iterable<E> target) {
    self = target;
  }

  public Bucket<E> filter(Predicate<E> pred) {
    List<E> res = new ArrayList<>();
    for (E e : self) {
      if (pred.test(e)) {
        res.add(e);
      }
    }
    return new Bucket<>(res);
  }

//  forEach
//  public void forEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }

  public <F> Bucket<F> map(Function<E, F> op){
    List<F> res = new ArrayList<>();
    for (E e : self) {
      F f = op.apply(e);
      if (f != null) { // Violates the math rules for functors but
        // hey, this is practical
        res.add(f);
      }
    }
    return new Bucket<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
