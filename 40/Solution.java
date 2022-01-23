import java.util.stream.IntStream;

public class Solution {
  static final int LIMIT = 1_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    StringBuilder fraction = new StringBuilder();
    for (int i = 1; fraction.length() < LIMIT; ++i) {
      fraction.append(i);
    }

    assert getDigit(fraction, 12) == 1;

    return IntStream.of(1, 10, 100, 1000, 10000, 100000, 1000000)
        .map(i -> getDigit(fraction, i))
        .reduce((x, y) -> x * y)
        .getAsInt();
  }

  static int getDigit(StringBuilder fraction, int pos) {
    return fraction.charAt(pos - 1) - '0';
  }
}