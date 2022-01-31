public class Solution {
  public static void main(String[] args) {
    assert solve(8) == 2;

    System.out.println(solve(1_000_000));
  }

  static int solve(int d) {
    int bestNumer = 0;
    int bestDenom = 1;
    for (int denom = 1; denom <= d; ++denom) {
      int numer = denom * 3 / 7 - ((denom * 3 % 7 == 0) ? 1 : 0);
      if ((long) numer * bestDenom > (long) bestNumer * denom) {
        int g = gcd(numer, denom);
        bestNumer = numer / g;
        bestDenom = denom / g;
      }
    }

    return bestNumer;
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}