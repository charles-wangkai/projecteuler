import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    String[] pairs =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p099_base_exp.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .toArray(String[]::new);

    return IntStream.range(0, pairs.length)
            .boxed()
            .max(
                Comparator.comparing(
                    i -> {
                      String[] parts = pairs[i].split(",");

                      return Integer.parseInt(parts[1]) * Math.log(Integer.parseInt(parts[0]));
                    }))
            .get()
        + 1;
  }
}