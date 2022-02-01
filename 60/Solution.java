import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  static long startTime = System.currentTimeMillis();
  static int minSum;

  public static void main(String[] args) {
    assert solve(4) == 792;

    System.out.println(solve(5));
  }

  static int solve(int primeNum) {
    minSum = Integer.MAX_VALUE;
    List<Integer> primes = new ArrayList<>();
    primes.add(2);
    while (minSum > primes.get(primes.size() - 1)) {
      primes.add(findNextPrime(primes.get(primes.size() - 1)));

      int[] primeIndices = new int[primeNum];
      primeIndices[0] = primes.size() - 1;
      search(primes, primeIndices, 1);

      long currentTime = System.currentTimeMillis();
      System.err.println(
          String.format(
              "[%ds] progress: %d / %d",
              (currentTime - startTime) / 1000, primes.get(primes.size() - 1), minSum));
    }

    return minSum;
  }

  static int findNextPrime(int x) {
    while (true) {
      ++x;
      if (isPrime(x)) {
        return x;
      }
    }
  }

  static boolean isPrime(long x) {
    for (int i = 2; (long) i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }

  static void search(List<Integer> primes, int[] primeIndices, int depth) {
    if (depth == primeIndices.length) {
      minSum = Math.min(minSum, Arrays.stream(primeIndices).map(primes::get).sum());

      return;
    }

    for (int i = primeIndices[depth - 1] - 1; i >= primeIndices.length - 1 - depth; --i) {
      primeIndices[depth] = i;
      if (check(primes, primeIndices, depth)) {
        search(primes, primeIndices, depth + 1);
      }
    }
  }

  static boolean check(List<Integer> primes, int[] primeIndices, int last) {
    for (int i = last - 1; i >= 0; --i) {
      if (!isPrime(
              Long.parseLong(
                  String.format(
                      "%d%d", primes.get(primeIndices[last]), primes.get(primeIndices[i]))))
          || !isPrime(
              Long.parseLong(
                  String.format(
                      "%d%d", primes.get(primeIndices[i]), primes.get(primeIndices[last]))))) {
        return false;
      }
    }

    return true;
  }
}
