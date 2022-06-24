import java.util.ArrayList;
import java.util.List;

public class Solution {
  static final int LIMIT = 1_000_000;

  public static void main(String[] args) {
    assert solve(3) == 30;
    assert solve(10) == 57;

    System.out.println(solve(50));
  }

  static int solve(int m) {
    List<Integer> dp = new ArrayList<>();
    dp.add(1);
    for (int i = 1; ; ++i) {
      int current = dp.get(i - 1);

      if (i >= m) {
        ++current;
      }

      for (int j = 0; j <= i - m - 1; ++j) {
        current += dp.get(j);
      }

      if (current > LIMIT) {
        return i;
      }

      dp.add(current);
    }
  }
}