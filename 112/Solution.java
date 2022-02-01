import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(50) == 538;
    assert solve(90) == 21780;

    System.out.println(solve(99));
  }

  static int solve(int percent) {
    int bouncyCount = 0;
    for (int i = 1; ; ++i) {
      if (!isIncreasing(i) && !isDecreasing(i)) {
        ++bouncyCount;
      }

      if (bouncyCount * 100 == i * percent) {
        return i;
      }
    }
  }

  static boolean isIncreasing(int x) {
    String s = String.valueOf(x);

    return IntStream.range(0, s.length() - 1).allMatch(i -> s.charAt(i) <= s.charAt(i + 1));
  }

  static boolean isDecreasing(int x) {
    String s = String.valueOf(x);

    return IntStream.range(0, s.length() - 1).allMatch(i -> s.charAt(i) >= s.charAt(i + 1));
  }
}