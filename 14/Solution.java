import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
  static Map<Long, Integer> cache = new HashMap<>();

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeChainLength(13) == 10;

    return IntStream.rangeClosed(1, 1_000_000)
        .boxed()
        .max(Comparator.comparing(i -> computeChainLength(i)))
        .get();
  }

  static int computeChainLength(long n) {
    if (!cache.containsKey(n)) {
      int result;
      if (n == 1) {
        result = 1;
      } else if (n % 2 == 0) {
        result = 1 + computeChainLength(n / 2);
      } else {
        result = 1 + computeChainLength(3 * n + 1);
      }

      cache.put(n, result);
    }

    return cache.get(n);
  }
}
