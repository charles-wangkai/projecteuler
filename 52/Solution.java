import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert isSameDigits(125874, 125874 * 2);

    for (int i = 1; ; ++i) {
      if (isSameDigits(i, 2 * i)
          && isSameDigits(i, 3 * i)
          && isSameDigits(i, 4 * i)
          && isSameDigits(i, 5 * i)
          && isSameDigits(i, 6 * i)) {
        return i;
      }
    }
  }

  static boolean isSameDigits(int x, int y) {
    return buildKey(x).equals(buildKey(y));
  }

  static String buildKey(int x) {
    return String.valueOf(x)
        .chars()
        .sorted()
        .mapToObj(ch -> String.valueOf((char) ch))
        .collect(Collectors.joining());
  }
}