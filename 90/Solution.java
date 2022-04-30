import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
  static final int[] SQUARES = IntStream.rangeClosed(1, 9).map(i -> i * i).toArray();

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    List<int[]> arrangements = new ArrayList<>();
    search(arrangements, new int[6], 0);

    int result = 0;
    for (int i = 0; i < arrangements.size(); ++i) {
      for (int j = i; j < arrangements.size(); ++j) {
        int i_ = i;
        int j_ = j;
        if (Arrays.stream(SQUARES)
            .allMatch(square -> canDisplay(arrangements.get(i_), arrangements.get(j_), square))) {
          ++result;
        }
      }
    }

    return result;
  }

  static boolean canDisplay(int[] arrangement1, int[] arrangement2, int value) {
    return (contain(arrangement1, value / 10) && contain(arrangement2, value % 10))
        || (contain(arrangement2, value / 10) && contain(arrangement1, value % 10));
  }

  static boolean contain(int[] arrangement, int digit) {
    return Arrays.stream(arrangement)
        .anyMatch(d -> d == digit || (d == 6 && digit == 9) || (d == 9 && digit == 6));
  }

  static void search(List<int[]> arrangements, int[] current, int index) {
    if (index == current.length) {
      arrangements.add(current.clone());

      return;
    }

    for (int i = (index == 0) ? 0 : (current[index - 1] + 1);
        i + (current.length - 1 - index) <= 9;
        ++i) {
      current[index] = i;
      search(arrangements, current, index + 1);
    }
  }
}