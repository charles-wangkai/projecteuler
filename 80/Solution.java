import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.IntStream;

public class Solution {
  static final int DIGIT_NUM = 100;
  static final MathContext MATH_CONTEXT = new MathContext(DIGIT_NUM + 5);
  static final int ITERATION_NUM = 400;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeDigitSum(2) == 475;

    return IntStream.rangeClosed(1, 100)
        .filter(i -> !isSquare(i))
        .map(Solution::computeDigitSum)
        .sum();
  }

  static boolean isSquare(int x) {
    int root = (int) Math.round(Math.sqrt(x));

    return root * root == x;
  }

  static int computeDigitSum(int n) {
    BigDecimal result = BigDecimal.valueOf(Math.sqrt(n));
    for (int i = 0; i < ITERATION_NUM; ++i) {
      result =
          result
              .add(BigDecimal.valueOf(n).divide(result, MATH_CONTEXT))
              .divide(BigDecimal.valueOf(2));
    }

    return result.toPlainString().replace(".", "").chars().limit(DIGIT_NUM).map(c -> c - '0').sum();
  }
}