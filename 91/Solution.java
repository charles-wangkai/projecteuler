public class Solution {
  public static void main(String[] args) {
    assert solve(2) == 14;

    System.out.println(solve(50));
  }

  static int solve(int limit) {
    int result = 0;
    for (int x1 = 0; x1 <= limit; ++x1) {
      for (int y1 = 0; y1 <= limit; ++y1) {
        if (!(x1 == 0 && y1 == 0)) {
          for (int x2 = 0; x2 <= limit; ++x2) {
            for (int y2 = 0; y2 <= limit; ++y2) {
              if ((x2 > x1 || (x2 == x1 && y2 > y1))
                  && (computeDotProduct(0, 0, x1, y1, x2, y2) == 0
                      || computeDotProduct(x1, y1, x2, y2, 0, 0) == 0
                      || computeDotProduct(x2, y2, 0, 0, x1, y1) == 0)) {
                ++result;
              }
            }
          }
        }
      }
    }

    return result;
  }

  static int computeDotProduct(
      int originX, int originY, int firstX, int firstY, int secondX, int secondY) {
    return (firstX - originX) * (secondX - originX) + (firstY - originY) * (secondY - originY);
  }
}