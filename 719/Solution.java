public class Solution {
  static long startTime = System.currentTimeMillis();

  public static void main(String[] args) {
    assert solve(10000) == 41333;

    System.out.println(solve(1_000_000_000_000L));
  }

  static long solve(long N) {
    long result = 0;
    for (int i = 1; (long) i * i <= N; ++i) {
      long square = (long) i * i;
      if (isSNumber(i, square)) {
        result += square;
      }

      long currentTime = System.currentTimeMillis();
      System.err.println(
          String.format("[%ds] progress: %d / %d", (currentTime - startTime) / 1000, square, N));
    }

    return result;
  }

  static boolean isSNumber(int root, long square) {
    int[] digits = String.valueOf(square).chars().map(c -> c - '0').toArray();
    for (int mask = 1; mask < 1 << (digits.length - 1); ++mask) {
      if (check(root, digits, mask)) {
        return true;
      }
    }

    return false;
  }

  static boolean check(int root, int[] digits, int mask) {
    long sum = 0;
    long value = 0;
    for (int i = 0; i < digits.length; ++i) {
      value = value * 10 + digits[i];

      if (i == digits.length - 1 || (mask & (1 << i)) != 0) {
        sum += value;
        value = 0;
      }
    }

    return sum == root;
  }
}