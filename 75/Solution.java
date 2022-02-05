// https://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple

import java.util.Arrays;

public class Solution {
  static final int LIMIT = 1_500_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] solutionCounts = new int[LIMIT + 1];
    for (int n = 1; n * n < solutionCounts.length; ++n) {
      for (int m = n + 1; ; ++m) {
        int a = m * m - n * n;
        int b = 2 * m * n;
        int c = m * m + n * n;
        if (a + b + c >= solutionCounts.length) {
          break;
        }

        if (gcd(m, n) == 1 && (m % 2 == 0 || n % 2 == 0)) {
          for (int i = a + b + c; i < solutionCounts.length; i += a + b + c) {
            ++solutionCounts[i];
          }
        }
      }
    }

    assert solutionCounts[12] == 1;
    assert solutionCounts[24] == 1;
    assert solutionCounts[30] == 1;
    assert solutionCounts[36] == 1;
    assert solutionCounts[40] == 1;
    assert solutionCounts[48] == 1;

    return (int) Arrays.stream(solutionCounts).filter(c -> c == 1).count();
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}