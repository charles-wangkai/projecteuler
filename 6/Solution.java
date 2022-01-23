import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 2640;

    System.out.println(solve(100));
  }

  static int solve(int n) {
    return square(IntStream.rangeClosed(1, n).sum())
        - IntStream.rangeClosed(1, n).map(Solution::square).sum();
  }

  static int square(int x) {
    return x * x;
  }
}
