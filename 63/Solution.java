import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int result = 0;
    for (int i = 1; BigInteger.valueOf(9).pow(i).toString().length() == i; ++i) {
      int i_ = i;
      result +=
          IntStream.rangeClosed(1, 9)
              .filter(base -> BigInteger.valueOf(base).pow(i_).toString().length() == i_)
              .count();
    }

    return result;
  }
}