import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert buildSolutions(50).equals(Set.of(28, 33, 49, 47));

    return buildSolutions(50_000_000).size();
  }

  static Set<Integer> buildSolutions(int n) {
    int[] primes = buildPrimes((int) Math.floor(Math.sqrt(n)));

    Set<Integer> result = new HashSet<>();
    for (int p1 : primes) {
      int square = p1 * p1;
      for (int p2 : primes) {
        int cube = p2 * p2 * p2;
        if (square + cube >= n) {
          break;
        }

        for (int p3 : primes) {
          int fourth = p3 * p3 * p3 * p3;
          if (square + cube + fourth >= n) {
            break;
          }

          result.add(square + cube + fourth);
        }
      }
    }

    return result;
  }

  static int[] buildPrimes(int limit) {
    boolean[] isPrimes = new boolean[limit + 1];
    Arrays.fill(isPrimes, 2, isPrimes.length, true);
    for (int i = 2; i < isPrimes.length; ++i) {
      if (isPrimes[i]) {
        for (int j = i + i; j < isPrimes.length; j += i) {
          isPrimes[j] = false;
        }
      }
    }

    return IntStream.range(0, isPrimes.length).filter(i -> isPrimes[i]).toArray();
  }
}