import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert isAmicable(220);
    assert isAmicable(284);

    return IntStream.range(1, 10000).filter(Solution::isAmicable).sum();
  }

  static boolean isAmicable(int n) {
    return d(n) != n && d(d(n)) == n;
  }

  static int d(int n) {
    return IntStream.range(1, n).filter(x -> n % x == 0).sum();
  }
}
