public class Solution {
  public static void main(String[] args) {
    assert solve(6) == 13;

    System.out.println(solve(10001));
  }

  static int solve(int k) {
    for (int i = 2; ; ++i) {
      if (isPrime(i)) {
        --k;

        if (k == 0) {
          return i;
        }
      }
    }
  }

  static boolean isPrime(int n) {
    for (int i = 2; i * i <= n; ++i) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}
