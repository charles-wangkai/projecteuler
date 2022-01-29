import java.math.BigInteger;

public class Solution {
  static final long MODULUS = 10_000_000_000L;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static String solve() {
    return String.format(
        "%010d",
        (28433
                    * BigInteger.TWO
                        .modPow(BigInteger.valueOf(7830457), BigInteger.valueOf(MODULUS))
                        .longValue()
                + 1)
            % MODULUS);
  }
}