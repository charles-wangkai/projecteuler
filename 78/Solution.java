// https://en.wikipedia.org/wiki/Partition_function_(number_theory)#Recurrence_relations

import java.util.ArrayList;
import java.util.List;

public class Solution {
  static final int MODULUS = 1_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    List<Integer> p = new ArrayList<>();
    p.add(1);
    for (int i = 1; ; ++i) {
      int s = 0;
      for (int j = 1; ; ++j) {
        int k = computePentagonal(j);
        if (k > i) {
          break;
        }

        s = addMod(s, ((j % 2 == 0) ? -1 : 1) * p.get(i - k));
      }
      for (int j = -1; ; --j) {
        int k = computePentagonal(j);
        if (k > i) {
          break;
        }

        s = addMod(s, ((j % 2 == 0) ? -1 : 1) * p.get(i - k));
      }

      if (i == 5) {
        assert s == 7;
      }

      if (s == 0) {
        return i;
      }

      p.add(s);
    }
  }

  static int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  static int computePentagonal(int n) {
    return n * (3 * n - 1) / 2;
  }
}