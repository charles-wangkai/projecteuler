import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(100) == 13;

    System.out.println(solve(1_000_000));
  }

  static int solve(int n) {
    return (int) IntStream.range(1, n).filter(Solution::isCircular).count();
  }

  static boolean isCircular(int x) {
    String s = String.valueOf(x);
    for (int i = 0; i < s.length(); ++i) {
      if (!isPrime(Integer.parseInt(String.format("%s%s", s.substring(i), s.substring(0, i))))) {
        return false;
      }
    }

    return true;
  }

  static boolean isPrime(int x) {
    if (x <= 1) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}