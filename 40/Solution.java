public class Solution {
  static final int LIMIT = 1_000_000;
  static final String fraction = buildFraction();

  public static void main(String[] args) {
    assert solve(12) == 1;

    System.out.println(
        solve(1)
            * solve(10)
            * solve(100)
            * solve(1000)
            * solve(10000)
            * solve(100000)
            * solve(1000000));
  }

  static String buildFraction() {
    StringBuilder result = new StringBuilder();
    for (int i = 1; result.length() < LIMIT; ++i) {
      result.append(i);
    }

    return result.toString();
  }

  static int solve(int n) {
    return fraction.charAt(n - 1) - '0';
  }
}