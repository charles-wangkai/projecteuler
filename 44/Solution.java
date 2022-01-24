public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    for (int i = 1; ; ++i) {
      int D = P(i);
      for (int f1 = 1; f1 * f1 <= 2 * D; ++f1) {
        if (D % f1 == 0) {
          int f2 = 2 * D / f1;
          if ((3 * f1 + f2 + 1) % 6 == 0) {
            int k = (3 * f1 + f2 + 1) / 6;
            int j = k - f1;
            if (j >= 1 && isPentagonal(P(j) + P(k))) {
              return D;
            }
          }
        }
      }
    }
  }

  static int P(int n) {
    return n * (3 * n - 1) / 2;
  }

  static boolean isPentagonal(int x) {
    int n = (int) Math.ceil(Math.sqrt(x * 2.0 / 3));

    return P(n) == x;
  }
}