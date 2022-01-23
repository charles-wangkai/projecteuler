import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] factorials = new int[10];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = i * factorials[i - 1];
    }

    int maxDigitNum = 1;
    int limit = 10;
    while ((maxDigitNum + 1) * factorials[9] >= limit) {
      ++maxDigitNum;
      limit *= 10;
    }

    return IntStream.range(3, limit)
        .filter(x -> String.valueOf(x).chars().map(ch -> factorials[ch - '0']).sum() == x)
        .sum();
  }
}
