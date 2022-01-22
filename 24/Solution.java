import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve(IntStream.range(0, 10).boxed().collect(Collectors.toList()), 999_999));
  }

  static String solve(List<Integer> digits, int k) {
    if (digits.isEmpty()) {
      return "";
    }

    Collections.sort(digits);

    int unit = computeFactorial(digits.size() - 1);
    int index = k / unit;
    int digit = digits.remove(index);

    return String.format("%d%s", digit, solve(digits, k % unit));
  }

  static int computeFactorial(int n) {
    return IntStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y);
  }
}