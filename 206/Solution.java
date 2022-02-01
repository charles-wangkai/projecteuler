public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static long solve() {
    for (int i = 0; ; ++i) {
      long current = 0;
      int rest = i;
      for (int j = 1; j <= 8; ++j) {
        current = (current * 10 + j) * 10 + rest % 10;

        rest /= 10;
      }
      current = (current * 10 + 9) * 100;

      Integer root = computeRoot(current);
      if (root != null) {
        return root;
      }
    }
  }

  static Integer computeRoot(long x) {
    int root = (int) Math.round(Math.sqrt(x));

    return ((long) root * root == x) ? root : null;
  }
}