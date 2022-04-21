public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int solutionNum = 0;
    for (int m = 1; ; ++m) {
      for (int i = 1; i <= 2 * m; ++i) {
        if (isSquare(m * m + i * i)) {
          solutionNum += i / 2 - Math.max(1, i - m) + 1;
        }
      }

      if (m == 99) {
        assert solutionNum == 1975;
      } else if (m == 100) {
        assert solutionNum == 2060;
      }

      if (solutionNum > 1_000_000) {
        return m;
      }
    }
  }

  static boolean isSquare(int x) {
    int root = (int) Math.round(Math.sqrt(x));

    return root * root == x;
  }
}