import java.math.BigInteger;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int result = 0;
    BigInteger numer = BigInteger.ONE;
    BigInteger denom = BigInteger.ONE;
    for (int i = 0; i < 1000; ++i) {
      BigInteger nextNumer = numer.add(BigInteger.TWO.multiply(denom));
      BigInteger nextDenom = numer.add(denom);

      numer = nextNumer;
      denom = nextDenom;
      if (numer.toString().length() > denom.toString().length()) {
        if (result == 0) {
          assert numer.intValue() == 1393;
          assert denom.intValue() == 985;
        }

        ++result;
      }
    }

    return result;
  }
}