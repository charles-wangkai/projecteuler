import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final int SIZE = 9;

  public static void main(String[] args) throws Throwable {
    System.out.println(solve());
  }

  static int solve() throws Throwable {
    assert Arrays.deepEquals(
        findSolution(
            new int[][] {
              {0, 0, 3, 0, 2, 0, 6, 0, 0},
              {9, 0, 0, 3, 0, 5, 0, 0, 1},
              {0, 0, 1, 8, 0, 6, 4, 0, 0},
              {0, 0, 8, 1, 0, 2, 9, 0, 0},
              {7, 0, 0, 0, 0, 0, 0, 0, 8},
              {0, 0, 6, 7, 0, 8, 2, 0, 0},
              {0, 0, 2, 6, 0, 9, 5, 0, 0},
              {8, 0, 0, 2, 0, 3, 0, 0, 9},
              {0, 0, 5, 0, 1, 0, 3, 0, 0}
            }),
        new int[][] {
          {4, 8, 3, 9, 2, 1, 6, 5, 7},
          {9, 6, 7, 3, 4, 5, 8, 2, 1},
          {2, 5, 1, 8, 7, 6, 4, 9, 3},
          {5, 4, 8, 1, 3, 2, 9, 7, 6},
          {7, 2, 9, 5, 6, 4, 1, 3, 8},
          {1, 3, 6, 7, 9, 8, 2, 4, 5},
          {3, 7, 2, 6, 8, 9, 5, 1, 4},
          {8, 1, 4, 2, 5, 3, 7, 6, 9},
          {6, 9, 5, 4, 1, 7, 3, 8, 2}
        });

    int[][][] grids =
        Arrays.stream(
                HttpClient.newHttpClient()
                    .send(
                        HttpRequest.newBuilder()
                            .uri(
                                URI.create(
                                    "https://projecteuler.net/project/resources/p096_sudoku.txt"))
                            .build(),
                        BodyHandlers.ofString())
                    .body()
                    .split("Grid.+"))
            .filter(s -> !s.isEmpty())
            .map(
                s ->
                    Arrays.stream(s.split("\n"))
                        .filter(l -> !l.isEmpty())
                        .map(l -> l.chars().map(c -> c - '0').toArray())
                        .toArray(int[][]::new))
            .toArray(int[][][]::new);

    return Arrays.stream(grids)
        .map(Solution::findSolution)
        .mapToInt(solution -> 100 * solution[0][0] + 10 * solution[0][1] + solution[0][2])
        .sum();
  }

  static int[][] findSolution(int[][] grid) {
    @SuppressWarnings("unchecked")
    Set<Integer>[][] candidateSets = new Set[SIZE][SIZE];
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        candidateSets[r][c] = IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toSet());
      }
    }
    boolean[][] determined = new boolean[SIZE][SIZE];

    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        if (grid[r][c] != 0) {
          determine(candidateSets, determined, r, c, grid[r][c]);
        }
      }
    }

    search(candidateSets, determined);

    int[][] result = new int[SIZE][SIZE];
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        result[r][c] = candidateSets[r][c].iterator().next();
      }
    }

    return result;
  }

  static void determine(
      Set<Integer>[][] candidateSets, boolean[][] determined, int row, int col, int d) {
    candidateSets[row][col] = new HashSet<>(Set.of(d));
    determined[row][col] = true;

    for (int c = 0; c < SIZE; ++c) {
      if (c != col) {
        candidateSets[row][c].remove(d);
      }
    }

    for (int r = 0; r < SIZE; ++r) {
      if (r != row) {
        candidateSets[r][col].remove(d);
      }
    }

    for (int r = row / 3 * 3; r < row / 3 * 3 + 3; ++r) {
      for (int c = col / 3 * 3; c < col / 3 * 3 + 3; ++c) {
        if (r != row || c != col) {
          candidateSets[r][c].remove(d);
        }
      }
    }
  }

  static boolean search(Set<Integer>[][] candidateSets, boolean[][] determined) {
    int minSize = Integer.MAX_VALUE;
    int bestR = -1;
    int bestC = -1;
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        if (!determined[r][c] && candidateSets[r][c].size() < minSize) {
          minSize = candidateSets[r][c].size();
          bestR = r;
          bestC = c;
        }
      }
    }

    if (minSize == Integer.MAX_VALUE) {
      return true;
    }
    if (minSize == 0) {
      return false;
    }

    for (int candidate : candidateSets[bestR][bestC].toArray(Integer[]::new)) {
      @SuppressWarnings("unchecked")
      Set<Integer>[][] nextCandidateSets = new Set[SIZE][SIZE];
      copy(candidateSets, nextCandidateSets);

      boolean[][] nextDetermined = new boolean[SIZE][SIZE];
      copy(determined, nextDetermined);

      determine(nextCandidateSets, nextDetermined, bestR, bestC, candidate);
      if (search(nextCandidateSets, nextDetermined)) {
        copy(nextCandidateSets, candidateSets);
        copy(nextDetermined, determined);

        return true;
      }
    }

    return false;
  }

  static void copy(Set<Integer>[][] srcCandidateSets, Set<Integer>[][] dstCandidateSets) {
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        dstCandidateSets[r][c] = new HashSet<>(srcCandidateSets[r][c]);
      }
    }
  }

  static void copy(boolean[][] srcDetermined, boolean[][] dstDetermined) {
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        dstDetermined[r][c] = srcDetermined[r][c];
      }
    }
  }
}