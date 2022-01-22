import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(5) == 15;

    System.out.println(solve(100));
  }

  static int solve(int n) {
    return (int)
        IntStream.rangeClosed(2, n)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(2, n).mapToObj(b -> BigInteger.valueOf(a).pow(b)))
            .distinct()
            .count();
  }
}