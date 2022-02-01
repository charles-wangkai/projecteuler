import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    System.out.println(solve());
  }

  static int solve() {
    List<int[]> threeCyclicSolutions = new ArrayList<>();
    search(
        threeCyclicSolutions,
        new Polygonal[] {new Triangle(), new Square(), new Pentagonal()},
        new int[3],
        0);

    assert threeCyclicSolutions.size() == 1;
    assert Arrays.equals(threeCyclicSolutions.get(0), new int[] {8128, 2882, 8281});

    List<int[]> sixCyclicSolutions = new ArrayList<>();
    search(
        sixCyclicSolutions,
        new Polygonal[] {
          new Triangle(),
          new Square(),
          new Pentagonal(),
          new Hexagonal(),
          new Heptagonal(),
          new Octagonal()
        },
        new int[6],
        0);

    assert sixCyclicSolutions.size() == 1;

    return Arrays.stream(sixCyclicSolutions.get(0)).sum();
  }

  static void search(List<int[]> solutions, Polygonal[] polygonals, int[] values, int index) {
    if (index == 0) {
      for (int value : polygonals[0].allValues) {
        values[0] = value;
        search(solutions, polygonals, values, 1);
      }

      return;
    }
    if (index == values.length - 1) {
      int value = values[index - 1] % 100 * 100 + values[0] / 100;
      if (polygonals[index].allValues.contains(value)) {
        values[index] = value;
        solutions.add(Arrays.copyOf(values, values.length));
      }

      return;
    }

    for (int i = index; i < polygonals.length; ++i) {
      swap(polygonals, i, index);

      for (int value :
          polygonals[index].prefixToValues.getOrDefault(values[index - 1] % 100, Set.of())) {
        values[index] = value;
        search(solutions, polygonals, values, index + 1);
      }

      swap(polygonals, i, index);
    }
  }

  static void swap(Polygonal[] polygonals, int index1, int index2) {
    Polygonal temp = polygonals[index1];
    polygonals[index1] = polygonals[index2];
    polygonals[index2] = temp;
  }
}

abstract class Polygonal {
  static final int LOWER = 1000;
  static final int UPPER = 9999;

  Map<Integer, Set<Integer>> prefixToValues = new HashMap<>();
  Set<Integer> allValues;

  Polygonal() {
    for (int i = 1; ; ++i) {
      int value = f(i);
      if (value > UPPER) {
        break;
      }

      if (value >= LOWER) {
        int prefix = value / 100;
        prefixToValues.putIfAbsent(prefix, new HashSet<>());
        prefixToValues.get(prefix).add(value);
      }
    }

    allValues = prefixToValues.values().stream().flatMap(Set::stream).collect(Collectors.toSet());
  }

  abstract int f(int n);
}

class Triangle extends Polygonal {
  @Override
  int f(int n) {
    return n * (n + 1) / 2;
  }
}

class Square extends Polygonal {
  @Override
  int f(int n) {
    return n * n;
  }
}

class Pentagonal extends Polygonal {
  @Override
  int f(int n) {
    return n * (3 * n - 1) / 2;
  }
}

class Hexagonal extends Polygonal {
  @Override
  int f(int n) {
    return n * (2 * n - 1);
  }
}

class Heptagonal extends Polygonal {
  @Override
  int f(int n) {
    return n * (5 * n - 3) / 2;
  }
}

class Octagonal extends Polygonal {
  @Override
  int f(int n) {
    return n * (3 * n - 2);
  }
}