public class Solution {
  public static void main(String[] args) {
    assert solve(10, 99) == 9009;

    System.out.println(solve(100, 999));
  }

  static int solve(int begin, int end) {
    int result = -1;
    for (int i = begin; i <= end; ++i) {
      for (int j = i; j <= end; ++j) {
        int product = i * j;
        if (isPalindrome(product)) {
          result = Math.max(result, product);
        }
      }
    }

    return result;
  }

  static boolean isPalindrome(int x) {
    return String.valueOf(x).equals(new StringBuilder(String.valueOf(x)).reverse().toString());
  }
}
