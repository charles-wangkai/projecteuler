import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
  static Map<Long, Integer> cache = new HashMap<>();

  public static void main(String[] args) {
    assert solve(13) == 10;

    System.out.println(
        IntStream.rangeClosed(1, 1_000_000)
            .asLongStream()
            .boxed()
            .max(Comparator.comparing(Solution::solve))
            .get());
  }

  static int solve(long n) {
    if (!cache.containsKey(n)) {
      int result;
      if (n == 1) {
        result = 1;
      } else if (n % 2 == 0) {
        result = 1 + solve(n / 2);
      } else {
        result = 1 + solve(3 * n + 1);
      }

      cache.put(n, result);
    }

    return cache.get(n);
  }
}
