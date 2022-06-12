package util;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import domain.transaction.Trader;
import domain.transaction.Transaction;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.springframework.lang.NonNull;

/**
 * This is java stream api practice for '5. Putting it all into practice' chapter in Modern java in
 * action
 */
public class TransactionTransformer {

  private TransactionTransformer() {
    throw new UnsupportedOperationException();
  }

  public static List<Transaction> getSortedByValue(
      @NonNull final List<Transaction> transactions,
      @NonNull final Predicate<Transaction> filterFunction) {
    return transactions.stream()
        .filter(filterFunction)
        .sorted(Comparator.comparingInt(Transaction::getValue))
        .collect(toList());
  }

  public static Set<String> getDistinctTraderCities(@NonNull final List<Transaction> transactions) {
    return transactions.stream()
        .map(Transaction::getTrader)
        .filter(Objects::nonNull)
        .map(Trader::getCity)
        .filter(Objects::nonNull)
        .collect(toSet());
  }

  public static List<Trader> getTradersSortedByName(
      @NonNull final List<Transaction> transactions,
      @NonNull final String cityNameToLive) {
    return transactions.stream()
        .map(Transaction::getTrader)
        .filter(Objects::nonNull)
        .sorted(Comparator.comparing(Trader::getName))
        .filter(trader -> Objects.equals(cityNameToLive, trader.getCity()))
        .collect(toList());
  }

  public static List<String> getSortedTraderNames(@NonNull final List<Transaction> transactions) {
    return transactions.stream()
        .map(Transaction::getTrader)
        .filter(Objects::nonNull)
        .map(Trader::getName)
        .filter(Objects::nonNull)
        .sorted()
        .distinct()
        .collect(toList());
  }

  public static boolean existsTraderIn(@NonNull final List<Transaction> transactions,
      @NonNull final String cityName) {
    return transactions.stream()
        .map(Transaction::getTrader)
        .filter(Objects::nonNull)
        .anyMatch(trader -> Objects.equals(trader.getCity(), cityName));
  }

  public static List<Integer> getTransactionValuesIn(@NonNull final List<Transaction> transactions,
      @NonNull final String cityName) {
    return transactions.stream()
        .filter(transaction -> Objects.nonNull(transaction.getTrader()))
        .filter(transaction -> Objects.equals(transaction.getTrader().getCity(), cityName))
        .map(Transaction::getValue)
        .collect(toList());
  }

  public static int getMaxValue(@NonNull final List<Transaction> transactions) {
    return transactions.stream()
        .max(Comparator.comparing(Transaction::getValue))
        .map(Transaction::getValue)
        .orElse(0);
  }

  public static int getMinValue(@NonNull final List<Transaction> transactions) {
    return transactions.stream()
        .min(Comparator.comparing(Transaction::getValue))
        .map(Transaction::getValue)
        .orElse(0);
  }
}
