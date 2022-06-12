package util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class FibonacciNumberSetGeneratorTest {

  @Test
  @DisplayName("입력한 갯수 만큼의 피보나치수열 집합을 반환한다.")
  public void shouldReturnFibonacciNumberSet() {
    // given
    var numberOfSets = 6;

    // when
    var results = FibonacciNumberSetGenerator.generate(numberOfSets);
    List<int[]> expectedResults = List.of(
        new int[]{0, 1},
        new int[]{1, 1},
        new int[]{1, 2},
        new int[]{2, 3},
        new int[]{3, 5},
        new int[]{5, 8}
    );

    // then
    assertEquals(results.size(), numberOfSets);
    assertEquals(results.size(), expectedResults.size());
    for (int i = 0; i < results.size(); i++) {
      assertArrayEquals(results.get(i), expectedResults.get(i));
    }
  }
}
