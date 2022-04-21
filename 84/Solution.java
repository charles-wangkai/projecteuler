import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final String BOARD[] = {
    "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", //
    "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3", //
    "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3", //
    "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"
  };
  static final double EPSILON = 1e-9;

  public static void main(String[] args) {
    assert solve(6).equals("102400");

    System.out.println(solve(4));
  }

  static String solve(int diceSide) {
    double[][] transition = buildTransition(diceSide);

    double[] p = new double[transition.length];
    p[0] = 1;
    while (true) {
      double[] nextP = multiply(p, transition);

      double[] p_ = p;
      if (IntStream.range(0, nextP.length).allMatch(i -> Math.abs(nextP[i] - p_[i]) < EPSILON)) {
        break;
      }

      p = nextP;
    }

    double[] probs = buildProbs(p);
    if (diceSide == 6) {
      assert equals(probs[10], 0.0624, 0.0001);
      assert equals(probs[24], 0.0318, 0.0001);
      assert equals(probs[0], 0.0309, 0.0001);
    }

    return IntStream.range(0, probs.length)
        .boxed()
        .sorted(Comparator.comparing((Integer i) -> probs[i]).reversed())
        .limit(3)
        .map(i -> String.format("%02d", i))
        .collect(Collectors.joining());
  }

  static double[][] buildTransition(int diceSide) {
    double[][] transition = new double[120][120];
    for (int from = 0; from < BOARD.length; ++from) {
      for (int doubleNum = 0; doubleNum < 3; ++doubleNum) {
        for (int dice1 = 1; dice1 <= diceSide; ++dice1) {
          for (int dice2 = 1; dice2 <= diceSide; ++dice2) {
            move(
                transition,
                from,
                doubleNum,
                dice1 + dice2,
                dice1 == dice2,
                1.0 / (diceSide * diceSide));
          }
        }
      }
    }

    return transition;
  }

  static void move(
      double[][] transition, int from, int doubleNum, int step, boolean isDouble, double prob) {
    int to = Math.floorMod(from + step, BOARD.length);

    int nextDoubleNum = isDouble ? (doubleNum + 1) : 0;
    if (nextDoubleNum == 3) {
      transition[toIndex(from, doubleNum)][toIndex(10, 0)] += prob;
    } else if (BOARD[to].equals("G2J")) {
      transition[toIndex(from, doubleNum)][toIndex(10, nextDoubleNum)] += prob;
    } else if (BOARD[to].startsWith("CC")) {
      transition[toIndex(from, doubleNum)][toIndex(to, nextDoubleNum)] += prob * 14 / 16;
      transition[toIndex(from, doubleNum)][toIndex(0, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(10, nextDoubleNum)] += prob / 16;
    } else if (BOARD[to].startsWith("CH")) {
      transition[toIndex(from, doubleNum)][toIndex(to, nextDoubleNum)] += prob * 6 / 16;
      transition[toIndex(from, doubleNum)][toIndex(0, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(10, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(11, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(24, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(39, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(5, nextDoubleNum)] += prob / 16;
      transition[toIndex(from, doubleNum)][toIndex(findNext(to, "R"), nextDoubleNum)] +=
          prob * 2 / 16;
      transition[toIndex(from, doubleNum)][toIndex(findNext(to, "U"), nextDoubleNum)] += prob / 16;
      move(transition, from, doubleNum, step - 3, isDouble, prob / 16);
    } else {
      transition[toIndex(from, doubleNum)][toIndex(to, nextDoubleNum)] += prob;
    }
  }

  static int findNext(int pos, String target) {
    while (true) {
      if (BOARD[pos].startsWith(target)) {
        return pos;
      }

      pos = (pos + 1) % BOARD.length;
    }
  }

  static int toIndex(int from, int doubleNum) {
    return from * 3 + doubleNum;
  }

  static boolean equals(double x, double y, double epsilon) {
    return Math.abs(x - y) < epsilon;
  }

  static double[] buildProbs(double[] p) {
    return IntStream.range(0, p.length / 3)
        .mapToDouble(i -> IntStream.range(0, 3).mapToDouble(j -> p[i * 3 + j]).sum())
        .toArray();
  }

  static double[] multiply(double[] p, double[][] transition) {
    double[] result = new double[p.length];
    for (int i = 0; i < result.length; ++i) {
      for (int j = 0; j < result.length; ++j) {
        result[i] += p[j] * transition[j][i];
      }
    }

    return result;
  }
}