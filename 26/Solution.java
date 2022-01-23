import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert computeCycle(6) == 1;
    assert computeCycle(7) == 6;

    return IntStream.range(2, 1000).boxed().max(Comparator.comparing(Solution::computeCycle)).get();
  }

  static int computeCycle(int d) {
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