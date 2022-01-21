import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 27;

    System.out.println(solve(100));
  }

  static int solve(int n) {
    return IntStream.rangeClosed(1, n)
        .mapToObj(BigInteger::valueOf)
        .reduce((x, y) -> x.multiply(y))
        .get()
        .toString()
        .chars()
        .map(ch -> ch - '0')
        .sum();
  }
}
