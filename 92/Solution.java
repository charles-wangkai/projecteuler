import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    Set<Integer> startFor89s =
        IntStream.range(1, 10_000_000)
            .filter(Solution::isArriveAt89)
            .boxed()
            .collect(Collectors.toSet());

    assert !startFor89s.contains(44);
    assert startFor89s.contains(85);

    return startFor89s.size();
  }

  static boolean isArriveAt89(int n) {
    if (n == 1) {
      return false;
    } else if (n == 89) {
      return true;
    }

    return isArriveAt89(String.valueOf(n).chars().map(ch -> (ch - '0') * (ch - '0')).sum());
  }
}