import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) throws Throwable {
    String[] names =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p022_names.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .replace("\"", "")
                    .split(","))
            .sorted()
            .toArray(String[]::new);

    assert names[937].equals("COLIN");
    assert solve("COLIN") == 53;

    System.out.println(IntStream.range(0, names.length).map(i -> (i + 1) * solve(names[i])).sum());
  }

  static int solve(String name) {
    return name.chars().map(ch -> ch - 'A' + 1).sum();
  }
}
