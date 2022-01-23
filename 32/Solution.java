import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    Set<Integer> products = new HashSet<>();
    search(products, IntStream.rangeClosed(1, 9).toArray(), 0);

    assert products.contains(7254);

    return products.stream().mapToInt(x -> x).sum();
  }

  static void search(Set<Integer> products, int[] digits, int index) {
    if (index == digits.length) {
      for (int length1 = 1; length1 < digits.length; ++length1) {
        int factor1 = buildNumber(digits, 0, length1 - 1);
        for (int length2 = 1; length1 + length2 < digits.length; ++length2) {
          int factor2 = buildNumber(digits, length1, length1 + length2 - 1);
          int product = buildNumber(digits, length1 + length2, digits.length - 1);
          if ((long) factor1 * factor2 == product) {
            products.add(product);
          }
        }
      }

      return;
    }

    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);
      search(products, digits, index + 1);
      swap(digits, i, index);
    }
  }

  static void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  static int buildNumber(int[] digits, int beginIndex, int endIndex) {
    int result = 0;
    for (int i = beginIndex; i <= endIndex; ++i) {
      result = result * 10 + digits[i];
    }

    return result;
  }
}
