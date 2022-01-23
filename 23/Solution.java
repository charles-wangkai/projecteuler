import java.util.stream.IntStream;

public class Solution {
  static final int LIMIT = 28123;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    int[] abundants = IntStream.rangeClosed(1, LIMIT).filter(Solution::isAbundant).toArray();

    boolean[] expressed = new boolean[LIMIT + 1];
    for (int a1 : abundants) {
      for (int a2 : abundants) {
        if (a1 + a2 >= expressed.length) {
          break;
        }

        expressed[a1 + a2] = true;
      }
    }

    return IntStream.range(0, expressed.length).filter(i -> !expressed[i]).sum();
  }

  static boolean isAbundant(int x) {
    return IntStream.range(1, x).filter(i -> x % i == 0).sum() > x;
  }
}