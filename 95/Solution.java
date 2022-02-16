import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  static final int LIMIT = 1_000_000;

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    List<Chain> chains = new ArrayList<>();
    boolean[] visited = new boolean[LIMIT + 1];
    for (int i = 0; i < visited.length; ++i) {
      if (!visited[i]) {
        Chain chain = findChain(visited, i);
        if (chain != null) {
          chains.add(chain);
        }
      }
    }

    assert chains.stream().anyMatch(chain -> chain.smallest == 220 && chain.length == 2);
    assert chains.stream().anyMatch(chain -> chain.smallest == 12496 && chain.length == 5);

    int maxLength = chains.stream().mapToInt(c -> c.length).max().getAsInt();

    return chains.stream()
        .filter(c -> c.length == maxLength)
        .mapToInt(c -> c.smallest)
        .min()
        .getAsInt();
  }

  static Chain findChain(boolean[] visited, int start) {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    int current = start;
    while (true) {
      if (current < 0
          || current >= visited.length
          || (visited[current] && !valueToIndex.containsKey(current))) {
        return null;
      }
      if (visited[current]) {
        int length = valueToIndex.size() - valueToIndex.get(current);
        int smallest = current;
        for (int i = 0; i < length - 1; ++i) {
          current = computeDivisorSum(current);
          smallest = Math.min(smallest, current);
        }

        return new Chain(smallest, length);
      }

      visited[current] = true;
      valueToIndex.put(current, valueToIndex.size());
      current = computeDivisorSum(current);
    }
  }

  static int computeDivisorSum(int x) {
    int result = 0;
    for (int i = 1; i * i <= x; ++i) {
      if (x % i == 0) {
        result += i;
        if (i != 1 && i * i != x) {
          result += x / i;
        }
      }
    }

    return result;
  }
}

class Chain {
  int smallest;
  int length;

  Chain(int smallest, int length) {
    this.smallest = smallest;
    this.length = length;
  }
}