package domain.transaction;

public class Trader {
  private final String name;
  private final String city;

  public Trader(final String name, final String city) {
    this.name = name;
    this.city = city;
  }

  public static Trader of(final String name, final String city) {
    return new Trader(name, city);
  }

  public String getName() {
    return this.name;
  }

  public String getCity() {
    return this.city;
  }

  @Override
  public String toString() {
    return "Trader{" +
        "name='" + this.name + '\'' +
        ", city='" + this.city + '\'' +
        '}';
  }
}
