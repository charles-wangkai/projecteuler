import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final Comparator<Hand> HAND_COMPARATOR =
      (h1, h2) ->
          IntStream.range(0, h1.sortKeys.size())
              .map(i -> Integer.compare(h1.sortKeys.get(i), h2.sortKeys.get(i)))
              .filter(c -> c != 0)
              .findFirst()
              .getAsInt();

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert HAND_COMPARATOR.compare(new Hand("5H 5C 6S 7S KD"), new Hand("2C 3S 8S 8D TD")) < 0;
    assert HAND_COMPARATOR.compare(new Hand("5D 8C 9S JS AC"), new Hand("2C 5C 7D 8S QH")) > 0;
    assert HAND_COMPARATOR.compare(new Hand("2D 9C AS AH AC"), new Hand("3D 6D 7D TD QD")) < 0;
    assert HAND_COMPARATOR.compare(new Hand("4D 6S 9H QH QC"), new Hand("3D 6D 7H QD QS")) > 0;
    assert HAND_COMPARATOR.compare(new Hand("2H 2D 4C 4D 4S"), new Hand("3C 3D 3S 9S 9D")) > 0;

    Hand[][] poker =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p054_poker.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("\n"))
            .map(line -> new Hand[] {new Hand(line.substring(0, 14)), new Hand(line.substring(15))})
            .toArray(Hand[][]::new);

    return (int)
        Arrays.stream(poker)
            .filter(hands -> HAND_COMPARATOR.compare(hands[0], hands[1]) > 0)
            .count();
  }
}

class Hand {
  static final char[] VALUES = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
  static final Map<Character, Integer> VALUE_TO_INDEX =
      IntStream.range(0, VALUES.length).boxed().collect(Collectors.toMap(i -> VALUES[i], i -> i));

  List<Integer> sortKeys = new ArrayList<>();

  Hand(String s) {
    String[] cards =
        Arrays.stream(s.split(" "))
            .sorted(Comparator.comparing(c -> VALUE_TO_INDEX.get(c.charAt(0))))
            .toArray(String[]::new);
    int[] indices = Arrays.stream(cards).mapToInt(c -> VALUE_TO_INDEX.get(c.charAt(0))).toArray();

    boolean sameSuit = Arrays.stream(cards).map(card -> card.charAt(1)).distinct().count() == 1;

    if (Arrays.stream(indices).distinct().count() == 5 && indices[0] + 4 == indices[4]) {
      sortKeys.add(sameSuit ? 9 : 5);
      sortKeys.add(indices[4]);

      return;
    }

    if (indices[0] == indices[3] || indices[1] == indices[4]) {
      sortKeys.add(8);
      sortKeys.add(indices[1]);
      sortKeys.add(indices[(indices[0] == indices[3]) ? 4 : 0]);

      return;
    }

    if ((indices[0] == indices[2] && indices[3] == indices[4])
        || (indices[2] == indices[4] && indices[0] == indices[1])) {
      sortKeys.add(7);
      sortKeys.add(indices[2]);
      sortKeys.add(indices[(indices[0] == indices[2]) ? 4 : 0]);

      return;
    }

    if (sameSuit) {
      sortKeys.add(6);
      sortKeys.add(indices[4]);
      sortKeys.add(indices[3]);
      sortKeys.add(indices[2]);
      sortKeys.add(indices[1]);
      sortKeys.add(indices[0]);

      return;
    }

    if (indices[0] == indices[2] || indices[1] == indices[3] || indices[2] == indices[4]) {
      sortKeys.add(4);
      if (indices[0] == indices[2]) {
        sortKeys.add(indices[4]);
        sortKeys.add(indices[3]);
      } else if (indices[1] == indices[3]) {
        sortKeys.add(indices[4]);
        sortKeys.add(indices[0]);
      } else {
        sortKeys.add(indices[1]);
        sortKeys.add(indices[0]);
      }

      return;
    }

    if ((indices[0] == indices[1] && (indices[2] == indices[3] || indices[3] == indices[4]))
        || (indices[1] == indices[2] && indices[3] == indices[4])) {
      sortKeys.add(3);
      if (indices[0] == indices[1]) {
        if (indices[2] == indices[3]) {
          sortKeys.add(indices[3]);
          sortKeys.add(indices[1]);
          sortKeys.add(indices[4]);
        } else {
          sortKeys.add(indices[4]);
          sortKeys.add(indices[1]);
          sortKeys.add(indices[2]);
        }
      } else {
        sortKeys.add(indices[4]);
        sortKeys.add(indices[2]);
        sortKeys.add(indices[0]);
      }

      return;
    }

    if (Arrays.stream(indices).distinct().count() == 4) {
      sortKeys.add(2);
      if (indices[0] == indices[1]) {
        sortKeys.add(indices[1]);
        sortKeys.add(indices[4]);
        sortKeys.add(indices[3]);
        sortKeys.add(indices[2]);
      } else if (indices[1] == indices[2]) {
        sortKeys.add(indices[2]);
        sortKeys.add(indices[4]);
        sortKeys.add(indices[3]);
        sortKeys.add(indices[0]);
      } else if (indices[2] == indices[3]) {
        sortKeys.add(indices[3]);
        sortKeys.add(indices[4]);
        sortKeys.add(indices[1]);
        sortKeys.add(indices[0]);
      } else {
        sortKeys.add(indices[4]);
        sortKeys.add(indices[2]);
        sortKeys.add(indices[1]);
        sortKeys.add(indices[0]);
      }

      return;
    }

    sortKeys.add(1);
    for (int i = 4; i >= 0; --i) {
      sortKeys.add(indices[i]);
    }
  }
}
