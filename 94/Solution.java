// https://en.wikipedia.org/wiki/Heron%27s_formula

public class Solution {
  static final int PERIMETER_LIMIT = 1_000_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int result = 0;

    for (int i = 1; 6 * i + 4 <= PERIMETER_LIMIT; ++i) {
      if (isSquare((3L * i + 2) * i)) {
        result += 6 * i + 4;
      }
    }
    for (int i = 1; 6 * i + 2 <= PERIMETER_LIMIT; ++i) {
      if (isSquare((3L * i + 1) * (i + 1))) {
        result += 6 * i + 2;
      }
    }

    return result;
  }

  static boolean isSquare(long x) {
    int root = (int) Math.round(Math.sqrt(x));

    return (long) root * root == x;
  }
}