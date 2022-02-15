import java.math.BigInteger;

public class Solution {
  static final int DIGIT_NUM = 9;
  static final BigInteger MODULUS = BigInteger.TEN.pow(DIGIT_NUM);

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert findFirst(Solution::isEndPandigital) == 541;
    assert findFirst(Solution::isStartPandigital) == 2749;

    return findFirst(s -> isEndPandigital(s) && isStartPandigital(s));
  }

  static int findFirst(Checker checker) {
    BigInteger prev = BigInteger.ONE;
    BigInteger curr = BigInteger.ONE;
    for (int i = 3; ; ++i) {
      BigInteger next = prev.add(curr);
      if (checker.check(next)) {
        return i;
      }

      prev = curr;
      curr = next;
    }
  }

  static boolean isPrefixNinePandigital(String s) {
    if (s.length() < DIGIT_NUM) {
      return false;
    }

    boolean[] seen = new boolean[10];
    for (int i = 0; i < DIGIT_NUM; ++i) {
      char c = s.charAt(i);
      if (c == '0' || seen[c - '0']) {
        return false;
      }

      seen[c - '0'] = true;
    }

    return true;
  }

  static boolean isStartPandigital(BigInteger x) {
    return isPrefixNinePandigital(x.toString());
  }

  static boolean isEndPandigital(BigInteger x) {
    return isPrefixNinePandigital(String.valueOf(x.mod(MODULUS).intValue()));
  }
}

interface Checker {
  boolean check(BigInteger x);
}