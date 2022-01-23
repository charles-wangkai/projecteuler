import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 17;

    System.out.println(solve(2_000_000));
  }

  static long solve(int n) {
    boolean[] primes = new boolean[n];
    Arrays.fill(primes, true);

    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (long j = (long) i * i; j < primes.length; j += i) {
          primes[(int) j] = false;
        }
      }
    }

    return IntStream.range(2, primes.length).filter(i -> primes[i]).asLongStream().sum();
  }
}
