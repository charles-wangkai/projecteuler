import java.math.BigInteger;

public class Solution {
  public static void main(String[] args) {
    assert solve(15) == 26;

    System.out.println(solve(1000));
  }

  static int solve(int exponent) {
    return BigInteger.TWO.pow(exponent).toString().chars().map(ch -> ch - '0').sum();
  }
}
