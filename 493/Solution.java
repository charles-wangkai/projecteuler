public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static String solve() {
    return String.format("%.9f", (double) search(10, 7, 20, 0, 1) / C(70, 20));
  }

  static long search(int maxNumInColor, int restColorNum, int rest, int colorCount, long factor) {
    if (restColorNum == 0) {
      return (rest == 0) ? (factor * colorCount) : 0;
    }

    long result = 0;
    for (int i = 0; i <= maxNumInColor && i <= rest; ++i) {
      result +=
          search(
              maxNumInColor,
              restColorNum - 1,
              rest - i,
              colorCount + ((i == 0) ? 0 : 1),
              factor * C(maxNumInColor, i));
    }

    return result;
  }

  static long C(int n, int r) {
    long result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }
}
