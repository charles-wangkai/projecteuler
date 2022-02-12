// https://www.alpertron.com.ar/QUAD.HTM

import java.util.ArrayList;
import java.util.List;

public class Solution {
  static final long TOTAL_LIMIT = 1_000_000_000_000L;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  // r^2 + 2rb - b^2 - r + b = 0
  static long solve() {
    List<Long> blues = new ArrayList<>();
    long r = 0;
    long b = 1;
    while (true) {
      blues.add(b);
      if (r + b > TOTAL_LIMIT) {
        break;
      }

      long nextR = r + 2 * b - 1;
      long nextB = 2 * r + 5 * b - 2;
      r = nextR;
      b = nextB;
    }

    assert blues.contains(15L);
    assert blues.contains(85L);

    return blues.get(blues.size() - 1);
  }
}