package util;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.transaction.Trader;
import domain.transaction.Transaction;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TransactionTransformerTest {

  private static final String CAMBRIDGE = "Cambridge";
  private static final String MILAN = "Milan";
  
  @Test
  @DisplayName("2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.")
  public void shouldReturnAllTransactionThatIsSortedValueAsc() {
    // given
    var transactions = TransactionFactory.get();
    Predicate<Transaction> yearPredicate = transaction -> transaction.getYear() == 2011;

    // when
    var result = TransactionTransformer.getSortedByValue(transactions, yearPredicate);

    // then
    assertTrue(result.stream().allMatch(yearPredicate));
    assertEquals(0,
        result.stream()
            .map(Transaction::getValue)
            .reduce(0, (acc, value) -> acc > value ? 1 : 0)
    );
  }

  @Test
  @DisplayName("거래자가 근무하는 모든 도시를 중복 없이 나열하시오.")
  public void shouldReturnNotDuplicatedCitiesWhereTraderIsLivedInTransactions() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var results = TransactionTransformer.getDistinctTraderCities(transactions);

    // then
    assertEquals(results.size(), 2);
  }

  @Test
  @DisplayName("케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.")
  public void shouldReturnAllTradersThatIsNameOrderedInSpecificCity() {
    // given
    var transactions = TransactionFactory.get();

    // whenÎ
    var results = TransactionTransformer.getTradersSortedByName(transactions, CAMBRIDGE);
    var expectedTraderNames = Set.of("Alan", "Brian", "Raoul");

    // then
    assertEquals(results.size(), 4);
    assertEquals(expectedTraderNames, results.stream().map(Trader::getName).collect(toSet()));
  }

  @Test
  @DisplayName("모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.")
  public void shouldReturnAllTraderNamesThatIsSortedAlphabet() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var results = TransactionTransformer.getSortedTraderNames(transactions);
    var expectedTraderNames = List.of("Alan", "Brian", "Mario", "Raoul");

    // then
    assertEquals(results.size(), 4);
    assertEquals(expectedTraderNames, results);
  }

  @Test
  @DisplayName("밀라노에 거래자가 있는가?")
  public void shouldReturnBooleanWhetherExistsTradersInSpecificCity() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var result = TransactionTransformer.existsTraderIn(transactions, MILAN);

    // then
    assertTrue(result);
  }

  @Test
  @DisplayName("케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.")
  public void shouldReturnTransactionValueForTradersResidingInSpecificCity() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var results = TransactionTransformer.getTransactionValuesIn(transactions, CAMBRIDGE);
    var expectedResults = List.of(300, 1000, 400, 950);

    // then
    assertEquals(results.size(), 4);
    assertEquals(results, expectedResults);
  }

  @Test
  @DisplayName("전체 트랜잭션 중 최대값은 얼마인가?")
  public void shouldReturnMaxValueInTransactions() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var result = TransactionTransformer.getMaxValue(transactions);

    // then
    assertEquals(result, 1000);
  }

  @Test
  @DisplayName("전체 트랜잭션 중 최솟값은 얼마인가?")
  public void shouldReturnMinValueInTransactions() {
    // given
    var transactions = TransactionFactory.get();

    // when
    var result = TransactionTransformer.getMinValue(transactions);

    // then
    assertEquals(result, 300);
  }

  private static final class TransactionFactory {

    private static List<Transaction> get() {
      return List.of(
          Transaction.of(TraderFactory.brian(), 2011, 300),
          Transaction.of(TraderFactory.raoul(), 2012, 1000),
          Transaction.of(TraderFactory.raoul(), 2011, 400),
          Transaction.of(TraderFactory.mario(), 2012, 710),
          Transaction.of(TraderFactory.mario(), 2012, 700),
          Transaction.of(TraderFactory.alan(), 2012, 950)
      );
    }
  }

  private static final class TraderFactory {

    private static Trader raoul() {
      return Trader.of("Raoul", CAMBRIDGE);
    }

    private static Trader mario() {
      return Trader.of("Mario", MILAN);
    }

    private static Trader alan() {
      return Trader.of("Alan", CAMBRIDGE);
    }

    private static Trader brian() {
      return Trader.of("Brian", CAMBRIDGE);
    }
  }

}
