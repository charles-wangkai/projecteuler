import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    return IntStream.range(0, 1_000_000)
        .filter(x -> isPalindrome(String.valueOf(x)) && isPalindrome(Integer.toBinaryString(x)))
        .sum();
  }

  static boolean isPalindrome(String s) {
    return new StringBuilder(s).reverse().toString().equals(s);
  }
}
