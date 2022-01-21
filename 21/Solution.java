import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(220);
    assert solve(284);

    System.out.println(IntStream.range(1, 10000).filter(Solution::solve).sum());
  }

  static boolean solve(int n) {
    return d(n) != n && d(d(n)) == n;
  }

  static int d(int n) {
    return IntStream.range(1, n).filter(x -> n % x == 0).sum();
  }
}
