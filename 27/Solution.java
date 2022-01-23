
public class Solution {
  public static void main(String[] args) {
    assert solve(1, 41) == 40;
    assert solve(-79, 1601) == 80;

    int result = 0;
    int maxConsecutiveNum = -1;
    for (int a = -999; a <= 999; ++a) {
      for (int b = -1000; b <= 1000; ++b) {
        int consecutiveNum = solve(a, b);
        if (consecutiveNum > maxConsecutiveNum) {
          maxConsecutiveNum = consecutiveNum;
          result = a * b;
        }
      }
    }
    System.out.println(result);
  }

  static int solve(int a, int b) {
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