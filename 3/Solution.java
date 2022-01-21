public class Solution {
  public static void main(String[] args) {
    assert solve(13195) == 29;

    System.out.println(solve(600851475143L));
  }

  static long solve(long n) {
    long result = -1;
    for (int i = 2; (long) i * i <= n; ++i) {
      while (n % i == 0) {
        n /= i;
        result = i;
      }
    }
    if (n != 1) {
      result = n;
    }

    return result;
  }
}