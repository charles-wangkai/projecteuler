public class Solution {
  public static void main(String[] args) {
    assert solve(2) == 14;
    assert solve(3) == 644;

    System.out.println(solve(4));
  }

  static int solve(int n) {
    int count = 0;
    for (int i = 1; ; ++i) {
      if (computePrimeFactorNum(i) == n) {
        ++count;
        if (count == n) {
          return i - count + 1;
        }
      } else {
        count = 0;
      }
    }
  }

  static int computePrimeFactorNum(int x) {
    int result = 0;
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        ++result;
        while (x % i == 0) {
          x /= i;
        }
      }
    }
    if (x != 1) {
      ++result;
    }

    return result;
  }
}