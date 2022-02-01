import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
  static int[] factorials;

  public static void main(String[] args) {
    buildFactorials();

    System.out.println(solve());
  }

  static void buildFactorials() {
    factorials = new int[10];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = factorials[i - 1] * i;
    }
  }

  static int solve() {
    assert computeNonRepeatingTermNum(145) == 1;
    assert computeNonRepeatingTermNum(169) == 3;
    assert computeNonRepeatingTermNum(871) == 2;
    assert computeNonRepeatingTermNum(872) == 2;
    assert computeNonRepeatingTermNum(69) == 5;
    assert computeNonRepeatingTermNum(78) == 4;
    assert computeNonRepeatingTermNum(540) == 2;

    return (int)
        IntStream.range(1, 1_000_000).filter(i -> computeNonRepeatingTermNum(i) == 60).count();
  }

  static int computeNonRepeatingTermNum(int start) {
    Set<Integer> seen = new HashSet<>();
    int current = start;
    while (!seen.contains(current)) {
      seen.add(current);
      current = String.valueOf(current).chars().map(c -> factorials[c - '0']).sum();
    }

    return seen.size();
  }
}