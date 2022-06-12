package domain.transaction;

public class Transaction {

  private final Trader trader;
  private final int year;
  private final int value;

  public Transaction(final Trader trader, final int year, final int value) {
    this.trader = trader;
    this.year = year;
    this.value = value;
  }

  public static Transaction of(final Trader trader, final int year, final int value) {
    return new Transaction(trader, year, value);
  }

  public Trader getTrader() {
    return this.trader;
  }

  public int getYear() {
    return this.year;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "trader=" + this.trader +
        ", year=" + this.year +
        ", value=" + this.value +
        '}';
  }
}
