import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static long solve() throws Throwable {
    String[] words =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p098_words.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .replace("\"", "")
                    .split(","))
            .toArray(String[]::new);

    Map<String, List<String>> keyToAnagrams = new HashMap<>();
    for (String word : words) {
      String key = buildKey(word);
      keyToAnagrams.putIfAbsent(key, new ArrayList<>());
      keyToAnagrams.get(key).add(word);
    }

    long result = -1;
    for (List<String> anagrams : keyToAnagrams.values()) {
      for (int i = 0; i < anagrams.size(); ++i) {
        for (int j = i + 1; j < anagrams.size(); ++j) {
          result = Math.max(result, computeMaxSquare(anagrams.get(i), anagrams.get(j)));
        }
      }
    }

    return result;
  }

  static String buildKey(String word) {
    return word.chars()
        .sorted()
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
  }

  static long computeMaxSquare(String word1, String word2) {
    long result = -1;
    for (int i = 1; ; ++i) {
      long square = (long) i * i;
      if (String.valueOf(square).length() > word1.length()) {
        break;
      }

      if (String.valueOf(square).length() == word1.length()) {
        Map<Character, Integer> letterToDigit = buildLetterToDigit(word1, square);
        if (letterToDigit != null) {
          long value =
              Long.parseLong(
                  word2
                      .chars()
                      .map(c -> letterToDigit.get((char) c))
                      .mapToObj(String::valueOf)
                      .collect(Collectors.joining()));
          if (String.valueOf(value).length() == word2.length() && isSquare(value)) {
            result = Math.max(result, Math.max(square, value));
          }
        }
      }
    }

    return result;
  }

  static boolean isSquare(long x) {
    int root = (int) Math.round(Math.sqrt(x));

    return (long) root * root == x;
  }

  static Map<Character, Integer> buildLetterToDigit(String word, long x) {
    String s = String.valueOf(x);

    Map<Character, Integer> letterToDigit = new HashMap<>();
    boolean[] used = new boolean[10];
    for (int i = 0; i < word.length(); ++i) {
      char letter = word.charAt(i);
      int digit = s.charAt(i) - '0';
      if (letterToDigit.containsKey(letter)) {
        if (letterToDigit.get(letter) != digit) {
          return null;
        }
      } else if (used[digit]) {
        return null;
      } else {
        letterToDigit.put(letter, digit);
        used[digit] = true;
      }
    }

    return letterToDigit;
  }
}