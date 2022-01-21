import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 2640;

    System.out.println(solve(100));
  }

  static int solve(int limit) {
    return square(IntStream.rangeClosed(1, limit).sum())
        - IntStream.rangeClosed(1, limit).map(Solution::square).sum();
  }

  static int square(int x) {
    return x * x;
  }
}
