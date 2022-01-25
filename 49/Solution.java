import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
  static final int LIMIT = 10000;
  static final String ONE_SOLUTION = "148748178147";

  static boolean[] primes;

  public static void main(String[] args) {
    buildPrimes();

    System.out.println(solve());
  }

  static void buildPrimes() {
    primes = new boolean[LIMIT];
    Arrays.fill(primes, 2, primes.length, true);
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i + i; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }
  }

  static String solve() {
    assert find(1).isEmpty();
    assert find(2).isEmpty();
    assert find(3).isEmpty();

    Set<String> solutions = find(4);

    assert solutions.size() == 2;
    assert solutions.contains(ONE_SOLUTION);

    return solutions.stream().filter(solution -> !solution.equals(ONE_SOLUTION)).findAny().get();
  }

  static Set<String> find(int digitNum) {
    int lower = 1;
    for (int i = 0; i < digitNum - 1; ++i) {
      lower *= 10;
    }

    Set<String> result = new HashSet<>();
    for (int a = lower; a < lower * 10; ++a) {
      if (primes[a]) {
        for (int b = a + 1; 2 * b - a < lower * 10; ++b) {
          if (primes[b] && isPermutation(b, a)) {
            int c = 2 * b - a;
            if (primes[c] && isPermutation(c, a)) {
              result.add(String.format("%d%d%d", a, b, c));
            }
          }
        }
      }
    }

    return result;
  }

  static boolean isPermutation(int x, int y) {
    return buildKey(x).equals(buildKey(y));
  }

  static String buildKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}