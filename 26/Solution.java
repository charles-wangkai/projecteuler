import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(6) == 1;
    assert solve(7) == 6;

    System.out.println(
        IntStream.range(2, 1000).boxed().max(Comparator.comparing(Solution::solve)).get());
  }

  static int solve(int d) {
    Map<Integer, Integer> remainderToIndex = new HashMap<>();
    int remainder = 1;
    while (true) {
      if (remainder == 0) {
        return 0;
      }
      if (remainderToIndex.containsKey(remainder)) {
        return remainderToIndex.size() - remainderToIndex.get(remainder);
      }

      remainderToIndex.put(remainder, remainderToIndex.size());
      remainder = remainder * 10 % d;
    }
  }
}