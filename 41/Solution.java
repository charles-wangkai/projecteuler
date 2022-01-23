import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    assert isPrime(2143);

    for (int length = 9; ; --length) {
      int result = search(IntStream.rangeClosed(1, length).toArray(), 0);
      if (result != Integer.MIN_VALUE) {
        return result;
      }
    }
  }

  static int search(int[] digits, int index) {
    if (index == digits.length) {
      int value = 0;
      for (int digit : digits) {
        value = value * 10 + digit;
      }

      return isPrime(value) ? value : Integer.MIN_VALUE;
    }

    int result = Integer.MIN_VALUE;
    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);
      result = Math.max(result, search(digits, index + 1));
      swap(digits, i, index);
    }

    return result;
  }

  static void swap(int[] digits, int index1, int index2) {
    int temp = digits[index1];
    digits[index1] = digits[index2];
    digits[index2] = temp;
  }

  static boolean isPrime(int x) {
    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}