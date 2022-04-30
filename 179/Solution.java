import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] divisorNums = new int[10_000_001];
    for (int i = 2; i < divisorNums.length; ++i) {
      divisorNums[i] = computeDivisorNum(i);
    }

    assert divisorNums[14] == 4;
    assert divisorNums[15] == 4;

    return (int)
        IntStream.range(2, divisorNums.length - 1)
            .filter(i -> divisorNums[i] == divisorNums[i + 1])
            .count();
  }

  static int computeDivisorNum(int x) {
    int result = 1;
    for (int i = 2; i * i <= x; ++i) {
      int exponent = 0;
      while (x % i == 0) {
        ++exponent;
        x /= i;
      }
      result *= exponent + 1;
    }
    if (x != 1) {
      result *= 2;
    }

    return result;
  }
}