import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static String solve() {
    String result = null;
    int maxCap = -1;
    for (int d1 = 0; d1 <= 9; ++d1) {
      for (int d2 = d1 + 1; d2 <= 9; ++d2) {
        for (int d3 = d2 + 1; d3 <= 9; ++d3) {
          for (int d4 = d3 + 1; d4 <= 9; ++d4) {
            Set<Integer> values = evaluate(d1, d2, d3, d4);
            int cap = computeCap(values);

            if (d1 == 1 && d2 == 2 && d3 == 3 && d4 == 4) {
              assert values.size() == 31;
              assert values.stream().mapToInt(x -> x).max().getAsInt() == 36;
              assert cap == 28;
            }

            if (cap > maxCap) {
              maxCap = cap;
              result = String.format("%d%d%d%d", d1, d2, d3, d4);
            }
          }
        }
      }
    }

    return result;
  }

  static int computeCap(Set<Integer> values) {
    return IntStream.iterate(1, i -> i + 1).filter(i -> !values.contains(i)).findFirst().getAsInt()
        - 1;
  }

  static Set<Integer> evaluate(int d1, int d2, int d3, int d4) {
    Set<Integer> values = new HashSet<>();
    search(
        values,
        List.of(
            new Rational(d1, 1), new Rational(d2, 1), new Rational(d3, 1), new Rational(d4, 1)));

    return values;
  }

  static void search(Set<Integer> values, List<Rational> operands) {
    if (operands.size() == 1) {
      Rational operand = operands.get(0);

      if (operand.denom == 1 && operand.numer >= 1) {
        values.add(operand.numer);
      }
    } else {
      for (int i = 0; i < operands.size(); ++i) {
        Rational operand1 = operands.get(i);
        for (int j = 0; j < operands.size(); ++j) {
          Rational operand2 = operands.get(j);
          if (j != i) {
            int i_ = i;
            int j_ = j;
            List<Rational> nextOperands =
                IntStream.range(0, operands.size())
                    .filter(p -> p != i_ && p != j_)
                    .mapToObj(operands::get)
                    .collect(Collectors.toList());

            nextOperands.add(add(operand1, operand2));
            search(values, nextOperands);
            nextOperands.remove(nextOperands.size() - 1);

            nextOperands.add(subtract(operand1, operand2));
            search(values, nextOperands);
            nextOperands.remove(nextOperands.size() - 1);

            nextOperands.add(multiply(operand1, operand2));
            search(values, nextOperands);
            nextOperands.remove(nextOperands.size() - 1);

            if (operand2.numer != 0) {
              nextOperands.add(divide(operand1, operand2));
              search(values, nextOperands);
              nextOperands.remove(nextOperands.size() - 1);
            }
          }
        }
      }
    }
  }

  static Rational add(Rational r1, Rational r2) {
    return new Rational(r1.numer * r2.denom + r2.numer * r1.denom, r1.denom * r2.denom);
  }

  static Rational subtract(Rational r1, Rational r2) {
    return new Rational(r1.numer * r2.denom - r2.numer * r1.denom, r1.denom * r2.denom);
  }

  static Rational multiply(Rational r1, Rational r2) {
    return new Rational(r1.numer * r2.numer, r1.denom * r2.denom);
  }

  static Rational divide(Rational r1, Rational r2) {
    return new Rational(r1.numer * r2.denom, r1.denom * r2.numer);
  }
}

class Rational {
  int numer;
  int denom;

  Rational(int numer, int denom) {
    int g = gcd(numer, denom);

    this.numer = numer / g;
    this.denom = denom / g;

    if (this.denom < 0) {
      this.numer *= -1;
      this.denom *= -1;
    }
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}