import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  static final int LIMIT = 1000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] solutionNums = new int[LIMIT + 1];
    for (int a = 1; a < solutionNums.length; ++a) {
      for (int b = a + 1; b < solutionNums.length; ++b) {
        int squareSum = a * a + b * b;
        int c = (int) Math.round(Math.sqrt(squareSum));
        if (c * c == squareSum && a + b + c < solutionNums.length) {
          ++solutionNums[a + b + c];
        }
      }
    }

    assert solutionNums[120] == 3;

    return IntStream.rangeClosed(1, 1000)
        .boxed()
        .max(Comparator.comparing(i -> solutionNums[i]))
        .get();
  }
}