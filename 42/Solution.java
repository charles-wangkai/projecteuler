import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;

public class Solution {
  public static void main(String[] args) throws Throwable {
    String[] words =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p042_words.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .replace("\"", "")
                    .split(","))
            .toArray(String[]::new);

    assert solve("SKY");

    System.out.println(Arrays.stream(words).filter(Solution::solve).count());
  }

  static boolean solve(String word) {
    return isTriangleNumber(word.chars().map(ch -> ch - 'A' + 1).sum());
  }

  static boolean isTriangleNumber(int x) {
    int root = (int) Math.floor(Math.sqrt(2 * x));

    return root * (root + 1) == 2 * x;
  }
}