import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(100) == 41;
    assert solve(1000) == 953;

    System.out.println(solve(1_000_000));
  }

  static int solve(int n) {
    boolean[] isPrimes = new boolean[n];
    Arrays.fill(isPrimes, 2, isPrimes.length, true);
    for (int i = 2; i < isPrimes.length; ++i) {
      for (int j = i + i; j < isPrimes.length; j += i) {
        isPrimes[j] = false;
      }
    }

    int[] primes = IntStream.range(0, isPrimes.length).filter(i -> isPrimes[i]).toArray();

    int maxLength = -1;
    int result = -1;
    for (int beginIndex = 0; beginIndex < primes.length; ++beginIndex) {
      int sum = 0;
      for (int endIndex = beginIndex; endIndex < primes.length; ++endIndex) {
        sum += primes[endIndex];
        if (sum >= isPrimes.length) {
          break;
        }

        if (isPrimes[sum] && endIndex - beginIndex + 1 > maxLength) {
          maxLength = endIndex - beginIndex + 1;
          result = sum;
        }
      }
    }

    return result;
  }
}