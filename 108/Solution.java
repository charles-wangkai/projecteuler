import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeSolutionNum(4) == 3;

    return IntStream.iterate(1, i -> i + 1)
        .filter(i -> computeSolutionNum(i) > 1000)
        .findFirst()
        .getAsInt();
  }

  static int computeSolutionNum(int n) {
    int result = 1;
    for (int i = 2; i * i <= n; ++i) {
      int exponent = 0;
      while (n % i == 0) {
        ++exponent;
        n /= i;
      }

      result *= 2 * exponent + 1;
    }
    if (n != 1) {
      result *= 3;
    }
    result = (result + 1) / 2;

    return result;
  }
}