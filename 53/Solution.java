public class Solution {
  static final int LIMIT = 1_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[][] c = new int[101][101];
    c[0][0] = 1;
    for (int n = 1; n < c.length; ++n) {
      for (int r = 0; r <= n; ++r) {
        c[n][r] = Math.min(2 * LIMIT, c[n - 1][r] + ((r == 0) ? 0 : c[n - 1][r - 1]));
      }
    }

    assert c[5][3] == 10;
    assert c[23][10] == 1144066;

    int result = 0;
    for (int n = 0; n < c.length; ++n) {
      for (int r = 0; r <= n; ++r) {
        if (c[n][r] > LIMIT) {
          ++result;
        }
      }
    }

    return result;
  }
}