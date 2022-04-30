import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(6) == 12951;
    assert solve(10) == 277032;

    System.out.println(solve(100));
  }

  static long solve(int digitNum) {
    return computeIncreasingNum(digitNum) + computeDecreasingNum(digitNum) - 9 * digitNum;
  }

  static long computeIncreasingNum(int digitNum) {
    long result = 9;

    long[] dp = new long[10];
    Arrays.fill(dp, 1);
    for (int i = 0; i < digitNum - 1; ++i) {
      long[] nextDp = new long[dp.length];
      for (int j = 0; j < nextDp.length; ++j) {
        for (int k = j; k < dp.length; ++k) {
          nextDp[j] += dp[k];
        }
      }

      result += IntStream.range(1, nextDp.length).mapToLong(j -> nextDp[j]).sum();

      dp = nextDp;
    }

    return result;
  }

  static long computeDecreasingNum(int digitNum) {
    long result = 9;

    long[] dp = new long[10];
    Arrays.fill(dp, 1);
    for (int i = 0; i < digitNum - 1; ++i) {
      long[] nextDp = new long[dp.length];
      for (int j = 0; j < nextDp.length; ++j) {
        for (int k = 0; k <= j; ++k) {
          nextDp[j] += dp[k];
        }
      }

      result += IntStream.range(1, nextDp.length).mapToLong(j -> nextDp[j]).sum();

      dp = nextDp;
    }

    return result;
  }
}