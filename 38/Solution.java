import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final int LIMIT = 1_000_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert isNinePandigital(192384576);
    assert isNinePandigital(918273645);

    int result = -1;
    for (int n = 2; n <= 9; ++n) {
      for (int i = 1; ; ++i) {
        int i_ = i;
        long value =
            Long.parseLong(
                IntStream.rangeClosed(1, n)
                    .mapToObj(j -> String.valueOf(i_ * j))
                    .collect(Collectors.joining()));
        if (value >= LIMIT) {
          break;
        }

        if (isNinePandigital((int) value)) {
          result = Math.max(result, (int) value);
        }
      }
    }

    return result;
  }

  static boolean isNinePandigital(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining())
        .equals("123456789");
  }
}