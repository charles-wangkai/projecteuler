import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(4) == 19316;

    System.out.println(solve(5));
  }

  static int solve(int exponent) {
    int maxDigitNum = 1;
    while ((maxDigitNum + 1) * pow(9, exponent) >= pow(10, maxDigitNum)) {
      ++maxDigitNum;
    }

    return IntStream.range(2, pow(10, maxDigitNum + 1))
        .filter(x -> String.valueOf(x).chars().map(ch -> pow(ch - '0', exponent)).sum() == x)
        .sum();
  }

  static int pow(int base, int exponent) {
    return IntStream.range(0, exponent).reduce(1, (x, y) -> x * base);
  }
}
