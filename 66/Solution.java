// http://en.wikipedia.org/wiki/Pell%27s_equation#Fundamental_solution_via_continued_fractions
// http://en.wikipedia.org/wiki/Generalized_continued_fraction

import java.math.BigInteger;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    assert solve(7) == 5;

    System.out.println(solve(1000));
  }

  static int solve(int n) {
    return IntStream.rangeClosed(1, n)
        .filter(i -> !isSquare(i))
        .boxed()
        .max(Comparator.comparing(Solution::computeMinX))
        .get();
  }

  static boolean isSquare(long x) {
    int root = (int) Math.round(Math.sqrt(x));

    return root * root == x;
  }

  static BigInteger computeMinX(int D) {
    // F = bn + (up1 * sqrt(D) + up2) / down

    int root = (int) Math.floor(Math.sqrt(D));

    int bn = root;
    int up1 = 1;
    int up2 = root;
    int down = D - root * root;

    BigInteger An_2 = BigInteger.ZERO;
    BigInteger Bn_2 = BigInteger.ONE;
    BigInteger An_1 = BigInteger.ONE;
    BigInteger Bn_1 = BigInteger.ZERO;
    while (true) {
      BigInteger An = An_1.multiply(BigInteger.valueOf(bn)).add(An_2);
      BigInteger Bn = Bn_1.multiply(BigInteger.valueOf(bn)).add(Bn_2);
      if (f(An, Bn, D).equals(BigInteger.ONE)) {
        return An;
      }

      An_2 = An_1;
      Bn_2 = Bn_1;
      An_1 = An;
      Bn_1 = Bn;

      bn = (up1 * root + up2) / down;
      up2 -= bn * down;

      int nextUp1 = up1 * down;
      int nextUp2 = -up2 * down;
      int nextDown = up1 * up1 * D - up2 * up2;
      int g = gcd(gcd(Math.abs(nextUp1), Math.abs(nextUp2)), Math.abs(nextDown));
      up1 = nextUp1 / g;
      up2 = nextUp2 / g;
      down = nextDown / g;
    }
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  static BigInteger f(BigInteger x, BigInteger y, int D) {
    return x.pow(2).subtract(y.pow(2).multiply(BigInteger.valueOf(D)));
  }
}