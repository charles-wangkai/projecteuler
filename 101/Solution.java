// https://en.wikipedia.org/wiki/Lagrange_polynomial

import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(new int[] {0, 0, 0, 1}) == 74;

    System.out.println(solve(new int[] {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1}));
  }

  static long solve(int[] coeffs) {
    long result = 0;
    for (int k = 1; k < coeffs.length; ++k) {
      long op = computeOP(coeffs, k);
      if (op != u(coeffs, k + 1)) {
        result += op;
      }
    }

    return result;
  }

  static long computeOP(int[] coeffs, int k) {
    return IntStream.range(0, k).mapToLong(i -> u(coeffs, i + 1) * L(k, i + 1, k + 1)).sum();
  }

  static long L(int interpolationNum, int index, int x) {
    long result = 1;
    for (int i = 1; i <= interpolationNum; ++i) {
      if (i != index) {
        result *= x - i;
      }
    }
    for (int i = 1; i <= interpolationNum; ++i) {
      if (i != index) {
        result /= index - i;
      }
    }

    return result;
  }

  static long u(int[] coeffs, int n) {
    long result = 0;
    long power = 1;
    for (int coeff : coeffs) {
      result += coeff * power;
      power *= n;
    }

    return result;
  }
}