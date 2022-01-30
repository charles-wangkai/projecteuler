import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static String solve() throws Throwable {
    String[] keylog =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p079_keylog.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .toArray(String[]::new);

    Queue<Element> queue = new ArrayDeque<>();
    queue.offer(new Element("", Arrays.stream(keylog).collect(Collectors.toSet())));
    while (true) {
      Element head = queue.poll();
      if (head.restLogs.isEmpty()) {
        return head.passcode;
      }

      Set<Character> firsts =
          head.restLogs.stream().map(r -> r.charAt(0)).collect(Collectors.toSet());
      Optional<Character> onlyFirst =
          firsts.stream()
              .filter(f -> head.restLogs.stream().allMatch(r -> r.indexOf(f) <= 0))
              .findAny();
      if (onlyFirst.isPresent()) {
        insertNext(queue, head, onlyFirst.get());
      } else {
        for (char first : firsts) {
          insertNext(queue, head, first);
        }
      }
    }
  }

  static void insertNext(Queue<Element> queue, Element head, char next) {
    queue.offer(
        new Element(
            head.passcode + next,
            head.restLogs.stream()
                .map(r -> (r.charAt(0) == next) ? r.substring(1) : r)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet())));
  }
}

class Element {
  String passcode;
  Set<String> restLogs;

  Element(String passcode, Set<String> restLogs) {
    this.passcode = passcode;
    this.restLogs = restLogs;
  }
}