public class Solution {
  public static void main(String[] args) {
    assert solve(2) == 6;

    System.out.println(solve(20));
  }

  static long solve(int size) {
    return C(size * 2, size);
  }

  static long C(int n, int r) {
    long result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }
}
