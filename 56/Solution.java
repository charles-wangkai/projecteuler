import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeDigitSum(10, 100) == 1;
    assert computeDigitSum(100, 100) == 1;

    return IntStream.range(1, 100)
        .flatMap(a -> IntStream.range(1, 100).map(b -> computeDigitSum(a, b)))
        .max()
        .getAsInt();
  }

  static int computeDigitSum(int a, int b) {
    return BigInteger.valueOf(a).pow(b).toString().chars().map(ch -> ch - '0').sum();
  }
}