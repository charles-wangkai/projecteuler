import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    boolean[] primes = new boolean[10_000_000];
    Arrays.fill(primes, true);
    int[] phis = IntStream.range(0, primes.length).toArray();
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i; j < primes.length; j += i) {
          if (j != i) {
            primes[j] = false;
          }

          phis[j] = phis[j] / i * (i - 1);
        }
      }
    }

    assert phis[87109] == 79180;

    return IntStream.range(2, primes.length)
        .filter(i -> buildKey(phis[i]).equals(buildKey(i)))
        .boxed()
        .min((i1, i2) -> Long.compare((long) i1 * phis[i2], (long) i2 * phis[i1]))
        .get();
  }

  static String buildKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
  }
}