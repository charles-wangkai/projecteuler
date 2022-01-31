import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 17;

    System.out.println(solve(100));
  }

  static int solve(int n) {
    List<Integer> constants = new ArrayList<>();
    constants.add(2);
    for (int i = 0; i < n - 1; ++i) {
      if (i % 3 == 1) {
        constants.add(2 * (i / 3 + 1));
      } else {
        constants.add(1);
      }
    }

    BigInteger numer = BigInteger.ONE;
    BigInteger denom = BigInteger.ZERO;
    for (int i = n - 1; i >= 0; --i) {
      BigInteger nextNumer = BigInteger.valueOf(constants.get(i)).multiply(numer).add(denom);
      BigInteger nextDenom = numer;

      numer = nextNumer;
      denom = nextDenom;
    }

    return numer.toString().chars().map(c -> c - '0').sum();
  }
}