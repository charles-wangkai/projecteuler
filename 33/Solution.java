import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    List<Rational> fractions = new ArrayList<>();
    for (int numerD1 = 1; numerD1 <= 9; ++numerD1) {
      for (int numerD2 = 0; numerD2 <= 9; ++numerD2) {
        int numer = numerD1 * 10 + numerD2;
        for (int denomD1 = 1; denomD1 <= 9; ++denomD1) {
          for (int denomD2 = 0; denomD2 <= 9; ++denomD2) {
            int denom = denomD1 * 10 + denomD2;
            if (numer < denom
                && ((numerD1 == denomD2 && numer * denomD1 == denom * numerD2)
                    || (numerD2 == denomD1 && numer * denomD2 == denom * numerD1))) {
              fractions.add(new Rational(numer, denom));
            }
          }
        }
      }
    }

    assert fractions.size() == 4;

    int numerProduct =
        fractions.stream().mapToInt(r -> r.numerator).reduce((x, y) -> x * y).getAsInt();
    int denomProduct =
        fractions.stream().mapToInt(r -> r.denominator).reduce((x, y) -> x * y).getAsInt();

    return denomProduct / gcd(numerProduct, denomProduct);
  }

  static int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}

class Rational {
  int numerator;
  int denominator;

  Rational(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }
}