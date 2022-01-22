import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  static final long MODULUS = 10_000_000_000L;

  public static void main(String[] args) {
    assert solve(10).equals("0405071317");

    System.out.println(solve(1000));
  }

  static String solve(int n) {
    String s =
        IntStream.rangeClosed(1, n)
            .mapToObj(i -> BigInteger.valueOf(i).pow(i))
            .reduce((x, y) -> x.add(y))
            .get()
            .toString();

    return s.substring(s.length() - 10);
  }
}