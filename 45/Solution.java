public class Solution {
  public static void main(String[] args) {
    assert solve(40755) == 40755;

    System.out.println(solve(40756));
  }

  static int solve(int begin) {
    for (int i = (int) Math.ceil(Math.sqrt(begin / 2.0)); ; ++i) {
      int value = i * (2 * i - 1);
      if (value >= begin && isTriangle(value) && isPentagonal(value)) {
        return value;
      }
    }
  }

  static boolean isTriangle(int x) {
    int n = (int) Math.floor(Math.sqrt(x * 2.0));

    return n * (n + 1L) / 2 == x;
  }

  static boolean isPentagonal(int x) {
    int n = (int) Math.ceil(Math.sqrt(x * 2.0 / 3));

    return n * (3L * n - 1) / 2 == x;
  }
}