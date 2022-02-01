import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(13) == 4;

    System.out.println(solve(10000));
  }

  static int solve(int n) {
    return (int)
        IntStream.rangeClosed(1, n)
            .filter(i -> !isSquare(i) && computePeriodNum(i) % 2 != 0)
            .count();
  }

  static boolean isSquare(int x) {
    int root = (int) Math.round(Math.sqrt(x));

    return root * root == x;
  }

  static int computePeriodNum(int x) {
    int root = (int) Math.floor(Math.sqrt(x));

    Map<State, Integer> stateToIndex = new HashMap<>();
    int up1 = 1;
    int up2 = 0;
    int down = 1;
    while (true) {
      State state = new State(up1, up2, down);
      if (stateToIndex.containsKey(state)) {
        return stateToIndex.size() - stateToIndex.get(state);
      }

      stateToIndex.put(state, stateToIndex.size());

      up2 -= (up1 * root + up2) / down * down;

      int nextUp1 = up1 * down;
      int nextUp2 = -up2 * down;
      int nextDown = up1 * up1 * x - up2 * up2;
      int g = gcd(gcd(Math.abs(nextUp1), Math.abs(nextUp2)), Math.abs(nextDown));

      up1 = nextUp1 / g;
      up2 = nextUp2 / g;
      down = nextDown / g;
    }
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

class State {
  int up1;
  int up2;
  int down;

  State(int up1, int up2, int down) {
    this.up1 = up1;
    this.up2 = up2;
    this.down = down;
  }

  @Override
  public int hashCode() {
    return Objects.hash(up1, up2, down);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return up1 == other.up1 && up2 == other.up2 && down == other.down;
  }
}