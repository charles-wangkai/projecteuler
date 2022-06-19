import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  static final int LIMIT = 100_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static long solve() {
    boolean[] primes = buildPrimes();

    boolean[] valids = new boolean[LIMIT + 1];
    Arrays.fill(valids, true);
    for (int i = 1; i * i <= LIMIT; ++i) {
      for (int j = i; i * j <= LIMIT; ++j) {
        if (!primes[i + j]) {
          valids[i * j] = false;
        }
      }
    }

    assert valids[30];

    return IntStream.range(1, valids.length).filter(i -> valids[i]).asLongStream().sum();
  }

  static boolean[] buildPrimes() {
    boolean[] primes = new boolean[LIMIT + 2];
    Arrays.fill(primes, 2, primes.length, true);
    for (int i = 2; i < primes.length; ++i) {
      if (primes[i]) {
        for (int j = i + i; j < primes.length; j += i) {
          primes[j] = false;
        }
      }
    }

    return primes;
  }
}