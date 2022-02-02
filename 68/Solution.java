import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static String solve() {
    assert buildSolutions(6)
        .equals(
            Set.of(
                "423531612",
                "432621513",
                "235451613",
                "253631415",
                "146362524",
                "164542326",
                "156264345",
                "165354246"));

    return buildSolutions(10).stream()
        .filter(s -> s.length() == 16)
        .max(Comparator.naturalOrder())
        .get();
  }

  static Set<String> buildSolutions(int n) {
    Set<String> solutions = new HashSet<>();
    search(solutions, IntStream.rangeClosed(1, n).toArray(), 0);

    return solutions;
  }

  static void search(Set<String> solutions, int[] values, int index) {
    int half = values.length / 2;

    if (index == values.length) {
      if (check(values)) {
        solutions.add(
            IntStream.range(0, half)
                .mapToObj(
                    i ->
                        String.format(
                            "%d%d%d", values[i], values[i + half], values[(i + 1) % half + half]))
                .collect(Collectors.joining()));
      }

      return;
    }

    for (int i = index; i < values.length; ++i) {
      if (index >= half || index == 0 || values[i] > values[0]) {
        swap(values, i, index);
        search(solutions, values, index + 1);
        swap(values, i, index);
      }
    }
  }

  static boolean check(int[] values) {
    int half = values.length / 2;

    return IntStream.range(0, half)
            .map(i -> values[i] + values[i + half] + values[(i + 1) % half + half])
            .distinct()
            .count()
        == 1;
  }

  static void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}