// https://projecteuler.net/thread=243#27959

public class Solution {
  public static void main(String[] args) {
    assert solve(4, 10) == 12;

    System.out.println(solve(15499, 94744));
  }

  static int solve(int numer, int denom) {
    int n = 1;
    int d = 1;
    for (int i = 2; ; ++i) {
      if (isPrime(i)) {
        n *= i - 1;
        d *= i;

        if (compare(n, d, numer, denom) < 0) {
          for (int j = 1; j < i; ++j) {
            if (compare((long) n * d * j, (long) d * (d * j - 1), numer, denom) < 0) {
              return d * j;
            }
          }
        }
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

  static int compare(long n1, long d1, int n2, int d2) {
    return Long.signum(n1 * d2 - n2 * d1);
  }
}