import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final int[] DIVISORS = {2, 3, 5, 7, 11, 13, 17};

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static long solve() {
    assert check(new int[] {1, 4, 0, 6, 3, 5, 7, 2, 8, 9});

    return search(IntStream.range(0, 10).toArray(), 0);
  }

  static long search(int[] digits, int index) {
    if (index == digits.length) {
      return check(digits)
          ? Long.parseLong(
              Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining()))
          : 0;
    }

    long result = 0;
    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);
      result += search(digits, index + 1);
      swap(digits, i, index);
    }

    return result;
  }

  static void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  static boolean check(int[] digits) {
    return IntStream.range(0, DIVISORS.length)
        .allMatch(
            i -> (100 * digits[i + 1] + 10 * digits[i + 2] + digits[i + 3]) % DIVISORS[i] == 0);
  }
}