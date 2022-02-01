import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(8) == 21;

    System.out.println(solve(1_000_000));
  }

  static long solve(int d) {
    return IntStream.rangeClosed(2, d).map(Solution::computePhi).asLongStream().sum();
  }

  static int computePhi(int x) {
    int result = x;
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        while (x % i == 0) {
          x /= i;
        }
        result = result / i * (i - 1);
      }
    }
    if (x >= 2) {
      result = result / x * (x - 1);
    }

    return result;
  }
}