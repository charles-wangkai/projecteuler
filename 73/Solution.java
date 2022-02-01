public class Solution {
  public static void main(String[] args) {
    assert solve(8) == 3;

    System.out.println(solve(12000));
  }

  static int solve(int d) {
    int result = 0;
    for (int denom = 1; denom <= d; ++denom) {
      int maxNumer = denom / 2 - ((denom % 2 == 0) ? 1 : 0);
      int minNumer = (denom + 2) / 3 + ((denom % 3 == 0) ? 1 : 0);

      for (int numer = minNumer; numer <= maxNumer; ++numer) {
        if (gcd(numer, denom) == 1) {
          ++result;
        }
      }
    }

    return result;
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}