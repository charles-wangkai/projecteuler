import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(10) == 23;

    System.out.println(solve(1000));
  }

  static int solve(int n) {
    return IntStream.range(1, n).filter(x -> x % 3 == 0 || x % 5 == 0).sum();
  }
}