import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10, 4) == 8;
    assert solve(10, 6) == 9;

    System.out.println(solve(100000, 10000));
  }

  static int solve(int n, int k) {
    int[] rads = new int[n + 1];
    for (int i = 1; i < rads.length; ++i) {
      rads[i] = computeRadical(i);
    }

    return IntStream.rangeClosed(1, n)
        .boxed()
        .sorted(Comparator.comparing((Integer i) -> rads[i]).thenComparing(i -> i))
        .skip(k - 1)
        .findFirst()
        .get();
  }

  static int computeRadical(int x) {
    int result = 1;
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        result *= i;
        while (x % i == 0) {
          x /= i;
        }
      }
    }
    result *= x;

    return result;
  }
}