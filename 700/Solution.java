import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
  static final long MODULUS = 4503599627370517L;
  static final long FACTOR = 1504170715041707L;
  static final long FACTOR_INV =
      BigInteger.valueOf(FACTOR).modInverse(BigInteger.valueOf(MODULUS)).longValue();
  static final int N_LIMIT = 100_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static long solve() {
    List<Long> eulercoins = new ArrayList<>();
    int limit = backwardSearch(eulercoins);
    forwardSearch(eulercoins, limit);

    assert eulercoins.contains(1504170715041707L);
    assert eulercoins.contains(8912517754604L);

    return eulercoins.stream().mapToLong(x -> x).sum();
  }

  static void forwardSearch(List<Long> eulercoins, int limit) {
    long minValue = Long.MAX_VALUE;
    long value = 0;
    for (int n = 1; n < limit; ++n) {
      value = (value + FACTOR) % MODULUS;
      if (value < minValue) {
        minValue = value;
        eulercoins.add(minValue);
      }
    }
  }

  static int backwardSearch(List<Long> eulercoins) {
    eulercoins.add(0L);
    long minN = Long.MAX_VALUE;
    long n = 0;
    for (int value = 1; ; ++value) {
      n = (n + FACTOR_INV) % MODULUS;
      if (n < minN) {
        minN = n;
        eulercoins.add((long) value);

        if (minN < N_LIMIT) {
          return (int) minN;
        }
      }
    }
  }
}