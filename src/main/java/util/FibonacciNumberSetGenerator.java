package util;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

/**
 * This is java stream api practice for '5-4. FibonacciNumber Set' chapter in Modern java in action
 */
public class FibonacciNumberSetGenerator {

  private FibonacciNumberSetGenerator() {
    throw new UnsupportedOperationException();
  }

  public static List<int[]> generate(final int numberOfSets) {
    return Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
        .limit(numberOfSets)
        .collect(toList());
  }

}
