import java.util.stream.IntStream;

public class Solution {
  static final String[] ONES = {
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  static final String[] TEENS = {
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen"
  };
  static final String[] TENS = {
    "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
  };

  public static void main(String[] args) {
    assert solve(1) == 3;
    assert solve(2) == 3;
    assert solve(3) == 5;
    assert solve(4) == 4;
    assert solve(5) == 4;
    assert solve(342) == 23;
    assert solve(115) == 20;

    System.out.println(IntStream.rangeClosed(1, 1000).map(Solution::solve).sum());
  }

  static int solve(int n) {
    if (n == 0) {
      return 0;
    } else if (n <= 9) {
      return ONES[n - 1].length();
    } else if (n <= 19) {
      return TEENS[n - 10].length();
    } else if (n <= 99) {
      return TENS[n / 10 - 2].length() + solve(n % 10);
    } else if (n <= 999) {
      return ONES[n / 100 - 1].length()
          + "hundred".length()
          + ((n % 100 == 0) ? 0 : "and".length())
          + solve(n % 100);
    }

    return ONES[0].length() + "thousand".length();
  }
}
