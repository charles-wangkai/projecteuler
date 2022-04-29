// https://projecteuler.net/best_posts=123

public class Solution {
  public static void main(String[] args) {
    assert solve(1_000_000_000) == 7037;

    System.out.println(solve(10_000_000_000L));
  }

  static int solve(long limit) {
    int pn = 2;
    for (int n = 2; ; ++n) {
      pn = findNextPrime(pn);
      if (n % 2 != 0 && 2L * n * pn > limit) {
        return n;
      }
    }
  }

  static int findNextPrime(int x) {
    while (true) {
      ++x;
      if (isPrime(x)) {
        return x;
      }
    }
  }

  static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
} 