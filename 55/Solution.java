import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {
  static final int MAX_ITERATION = 49;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert !isLychrel(349);
    assert isLychrel(196);
    assert isLychrel(4994);

    return (int) IntStream.range(1, 10000).filter(Solution::isLychrel).count();
  }

  static boolean isLychrel(int x) {
    BigInteger current = BigInteger.valueOf(x);
    for (int i = 0; i < MAX_ITERATION; ++i) {
      current =
          current.add(new BigInteger(new StringBuilder(current.toString()).reverse().toString()));
      if (isPalindrome(current.toString())) {
        return false;
      }
    }

    return true;
  }

  static boolean isPalindrome(String s) {
    return new StringBuilder(s).reverse().toString().equals(s);
  }
}