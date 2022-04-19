import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
  public static void main(String[] args) {
    assert solve(1000) == 4164;

    System.out.println(solve(100_000_000));
  }

  static long solve(int limit) {
    List<Integer> squares = new ArrayList<>();
    for (int i = 1; ; ++i) {
      squares.add(i * i);
      if (squares.size() >= 2
          && squares.get(squares.size() - 1) + squares.get(squares.size() - 2) >= limit) {
        break;
      }
    }

    Set<Integer> solutions = new HashSet<>();
    for (int i = 0; i < squares.size(); ++i) {
      int sum = squares.get(i);
      for (int j = i + 1; j < squares.size(); ++j) {
        sum += squares.get(j);
        if (sum >= limit) {
          break;
        }
        if (isPalindrome(sum)) {
          solutions.add(sum);
        }
      }
    }

    return solutions.stream().mapToLong(x -> x).sum();
  }

  static boolean isPalindrome(int x) {
    return new StringBuilder(String.valueOf(x)).reverse().toString().equals(String.valueOf(x));
  }
}