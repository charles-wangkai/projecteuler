// https://projecteuler.net/thread=120#4058

import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeRMax(7) == 42;

    return IntStream.rangeClosed(3, 1000).map(Solution::computeRMax).sum();
  }

  static int computeRMax(int a) {
    int result = 2;
    boolean[] seen = new boolean[a * a];
    int r = 2 * a;
    while (!seen[r]) {
      seen[r] = true;
      result = Math.max(result, r);

      r = (r + 4 * a) % seen.length;
    }

    return result;
  }
}