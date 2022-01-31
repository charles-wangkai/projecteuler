import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 6;

    System.out.println(solve(1_000_000));
  }

  static int solve(int n) {
    int[] phis = IntStream.rangeClosed(0, n).map(Solution::computePhi).toArray();

    return IntStream.rangeClosed(1, n)
        .boxed()
        .max((i1, i2) -> Long.compare((long) i1 * phis[i2], (long) i2 * phis[i1]))
        .get();
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