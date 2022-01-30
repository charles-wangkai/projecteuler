public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int total = 1;
    int primeCount = 0;
    for (int side = 3; ; side += 2) {
      total += 4;
      for (int i = 0; i < 4; ++i) {
        if (isPrime(side * side - i * (side - 1))) {
          ++primeCount;
        }
      }

      if (side == 7) {
        assert primeCount * 13 == total * 8;
      }

      if (primeCount * 10 < total) {
        return side;
      }
    }
  }

  static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}