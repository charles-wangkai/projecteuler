public class Solution {
  static final int TARGET = 2_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int result = -1;
    int minDiff = Integer.MAX_VALUE;
    for (int side1 = 1; side1 * (side1 + 1) / 2 <= TARGET; ++side1) {
      for (int i = 0; i < 2; ++i) {
        int side2 = (int) Math.floor(Math.sqrt(TARGET / (side1 * (side1 + 1) / 2) * 2)) + i;
        int diff = Math.abs((side1 * (side1 + 1) / 2) * (side2 * (side2 + 1) / 2) - TARGET);
        if (diff < minDiff) {
          minDiff = diff;
          result = side1 * side2;
        }
      }
    }

    return result;
  }
}