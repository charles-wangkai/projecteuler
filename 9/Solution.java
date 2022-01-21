public class Solution {
  static final int SUM = 1000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    for (int a = 1; ; ++a) {
      for (int b = a + 1; b < SUM - a - b; ++b) {
        int c = SUM - a - b;
        if (a * a + b * b == c * c) {
          return a * b * c;
        }
      }
    }
  }
}
