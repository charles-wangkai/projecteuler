import java.util.stream.IntStream;

public class Solution {
  static final int TRUNCATABLE_NUM = 11;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert isTruncatable(3797);

    int result = 0;
    int value = 10;
    for (int i = 0; i < TRUNCATABLE_NUM; ++i) {
      while (!isTruncatable(value)) {
        ++value;
      }

      result += value;
      ++value;
    }

    return result;
  }

  static boolean isTruncatable(int n) {
    String s = String.valueOf(n);

    return IntStream.range(0, s.length()).allMatch(i -> isPrime(Integer.parseInt(s.substring(i))))
        && IntStream.range(0, s.length())
            .allMatch(i -> isPrime(Integer.parseInt(s.substring(0, i + 1))));
  }

  static boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}