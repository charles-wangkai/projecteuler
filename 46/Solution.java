public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    for (int i = 3; ; i += 2) {
      if (!isPrime(i)) {
        boolean found = false;
        for (int j = 1; 2 * j * j < i; ++j) {
          if (isPrime(i - 2 * j * j)) {
            found = true;

            break;
          }
        }

        if (!found) {
          return i;
        }
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