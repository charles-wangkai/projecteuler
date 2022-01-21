import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
  public static void main(String[] args) {
    assert solve(5) == 28;

    System.out.println(solve(500));
  }

  static int solve(int divisorNumTarget) {
    for (int i = 1; ; ++i) {
      Map<Integer, Integer> primeToExponent =
          (i % 2 == 0)
              ? merge(buildPrimeToExponent(i / 2), buildPrimeToExponent(i + 1))
              : merge(buildPrimeToExponent(i), buildPrimeToExponent((i + 1) / 2));
      if (primeToExponent.values().stream().reduce(1, (x, y) -> x * (y + 1)) > divisorNumTarget) {
        return i * (i + 1) / 2;
      }
    }
  }

  static Map<Integer, Integer> buildPrimeToExponent(int n) {
    Map<Integer, Integer> primeToExponent = new HashMap<>();
    for (int i = 2; i * i <= n; ++i) {
      int exponent = 0;
      while (n % i == 0) {
        n /= i;
        ++exponent;
      }
      if (exponent != 0) {
        primeToExponent.put(i, exponent);
      }
    }
    if (n != 1) {
      primeToExponent.put(n, 1);
    }

    return primeToExponent;
  }

  static Map<Integer, Integer> merge(
      Map<Integer, Integer> primeToExponent1, Map<Integer, Integer> primeToExponent2) {
    return Stream.concat(primeToExponent1.keySet().stream(), primeToExponent2.keySet().stream())
        .distinct()
        .collect(
            Collectors.toMap(
                prime -> prime,
                prime ->
                    primeToExponent1.getOrDefault(prime, 0)
                        + primeToExponent2.getOrDefault(prime, 0)));
  }
}
