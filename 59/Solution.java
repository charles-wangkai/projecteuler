import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Solution {
  static final int KEY_LENGTH = 3;

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    int[] cipher =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p059_cipher.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split(","))
            .mapToInt(Integer::parseInt)
            .toArray();

    int[] key =
        IntStream.range(0, KEY_LENGTH)
            .map(
                i ->
                    IntStream.rangeClosed('a', 'z')
                        .boxed()
                        .max(
                            Comparator.comparing(
                                k ->
                                    IntStream.range(0, cipher.length)
                                        .filter(
                                            j ->
                                                j % KEY_LENGTH == i
                                                    && isCommon((char) (k ^ cipher[j])))
                                        .count()))
                        .get())
            .toArray();

    return IntStream.range(0, cipher.length).map(i -> key[i % KEY_LENGTH] ^ cipher[i]).sum();
  }

  static boolean isCommon(char ch) {
    return Character.isLetterOrDigit(ch) || Character.isWhitespace(ch);
  }
}