import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    assert solve(3) == 41063625;

    System.out.println(solve(5));
  }

  static long solve(int permutationNum) {
    Queue<String> queue = new ArrayDeque<>();
    Map<String, Long> keyToFirst = new HashMap<>();
    Map<String, Integer> keyToCount = new HashMap<>();
    for (int i = 1; ; ++i) {
      long cube = (long) i * i * i;
      String key =
          String.valueOf(cube)
              .chars()
              .sorted()
              .mapToObj(c -> String.valueOf((char) c))
              .collect(Collectors.joining());
      if (!keyToFirst.containsKey(key)) {
        keyToFirst.put(key, cube);
      }
      keyToCount.put(key, keyToCount.getOrDefault(key, 0) + 1);

      while (!queue.isEmpty() && queue.peek().length() < key.length()) {
        String k = queue.poll();
        if (keyToCount.get(k) == permutationNum) {
          return keyToFirst.get(k);
        }
      }

      queue.offer(key);
    }
  }
}