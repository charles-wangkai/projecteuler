import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 2520;

    System.out.println(solve(20));
  }

  static long solve(int limit) {
    return IntStream.rangeClosed(1, limit).asLongStream().reduce(Solution::lcm).getAsLong();
  }

  static long lcm(long x, long y) {
    return x * y / gcd(x, y);
  }

  static long gcd(long x, long y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}
