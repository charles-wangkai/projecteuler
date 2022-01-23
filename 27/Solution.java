public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeConsecutiveNum(1, 41) == 40;
    assert computeConsecutiveNum(-79, 1601) == 80;

    int result = 0;
    int maxConsecutiveNum = -1;
    for (int a = -999; a <= 999; ++a) {
      for (int b = -1000; b <= 1000; ++b) {
        int consecutiveNum = computeConsecutiveNum(a, b);
        if (consecutiveNum > maxConsecutiveNum) {
          maxConsecutiveNum = consecutiveNum;
          result = a * b;
        }
      }
    }

    return result;
  }

  static int computeConsecutiveNum(int a, int b) {
    for (int n = 0; ; ++n) {
      if (!isPrime(n * n + a * n + b)) {
        return n;
      }
    }
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