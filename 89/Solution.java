import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class Solution {
  static final String[] SYMBOLS = {
    "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
  };
  static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert toRoman(toDecimal("IIIIIIIIIIIIIIII")).equals("XVI");

    String[] roman =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p089_roman.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .toArray(String[]::new);

    return Arrays.stream(roman).mapToInt(r -> r.length() - toRoman(toDecimal(r)).length()).sum();
  }

  static int toDecimal(String s) {
    int result = 0;
    int beginIndex = 0;
    int index = 0;
    while (beginIndex != s.length()) {
      if (s.startsWith(SYMBOLS[index], beginIndex)) {
        result += VALUES[index];
        beginIndex += SYMBOLS[index].length();
      } else {
        ++index;
      }
    }

    return result;
  }

  static String toRoman(int x) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (x != 0) {
      if (x >= VALUES[index]) {
        result.append(SYMBOLS[index]);
        x -= VALUES[index];
      } else {
        ++index;
      }
    }

    return result.toString();
  }
}