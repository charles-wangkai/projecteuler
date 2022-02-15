import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static String solve() {
    List<Integer> pyramidalTotals = buildTotals(4, 9);
    List<Integer> cubicTotals = buildTotals(6, 6);

    return String.format(
        "%.7f",
        (double)
                pyramidalTotals.stream()
                    .mapToInt(p -> computeBeatNum(cubicTotals, p))
                    .asLongStream()
                    .sum()
            / pyramidalTotals.size()
            / cubicTotals.size());
  }

  static int computeBeatNum(List<Integer> cubicTotals, int pyramidalTotal) {
    int result = 0;
    int lower = 0;
    int upper = cubicTotals.size() - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (cubicTotals.get(middle) < pyramidalTotal) {
        result = middle + 1;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  static List<Integer> buildTotals(int sideNum, int diceNum) {
    List<Integer> totals = new ArrayList<>();
    search(totals, sideNum, diceNum, 0);
    Collections.sort(totals);

    return totals;
  }

  static void search(List<Integer> totals, int sideNum, int restDiceNum, int total) {
    if (restDiceNum == 0) {
      totals.add(total);

      return;
    }

    for (int i = 1; i <= sideNum; ++i) {
      search(totals, sideNum, restDiceNum - 1, total + i);
    }
  }
}