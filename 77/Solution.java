import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] dp = new int[100];
    dp[0] = 1;
    int[] primes = IntStream.range(0, dp.length).filter(Solution::isPrime).toArray();
    for (int prime : primes) {
      for (int i = prime; i < dp.length; ++i) {
        dp[i] += dp[i - prime];
      }
    }

    assert dp[10] == 5;

    return IntStream.range(0, dp.length).filter(i -> dp[i] > 5000).findFirst().getAsInt();
  }

  static boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}