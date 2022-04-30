import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(30) == 10;

    System.out.println(solve(100_000_000));
  }

  static int solve(int limit) {
    int[] primes = buildPrimes(limit);

    int result = 0;
    int rightIndex = primes.length - 1;
    for (int leftIndex = 0; ; ++leftIndex) {
      while (rightIndex >= leftIndex && primes[leftIndex] * primes[rightIndex] >= limit) {
        --rightIndex;
      }
      if (leftIndex > rightIndex) {
        break;
      }

      result += rightIndex - leftIndex + 1;
    }

    return result;
  }

  static int[] buildPrimes(int limit) {
    boolean[] isPrimes = new boolean[limit];
    Arrays.fill(isPrimes, true);
    for (int i = 2; i < isPrimes.length; ++i) {
      if (isPrimes[i]) {
        for (int j = 2 * i; j < isPrimes.length; j += i) {
          isPrimes[j] = false;
        }
      }
    }

    return IntStream.range(2, isPrimes.length).filter(i -> isPrimes[i]).toArray();
  }
}